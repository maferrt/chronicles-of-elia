package com.chroniclesofelia.api.progress.service;

import com.chroniclesofelia.api.auth.entity.AppUser;
import com.chroniclesofelia.api.learning.entity.Exercise;
import com.chroniclesofelia.api.learning.entity.ExerciseOption;
import com.chroniclesofelia.api.learning.entity.Mission;
import com.chroniclesofelia.api.learning.repository.ExerciseOptionRepository;
import com.chroniclesofelia.api.learning.repository.ExerciseRepository;
import com.chroniclesofelia.api.learning.repository.MissionRepository;
import com.chroniclesofelia.api.profiles.entity.UserProfile;
import com.chroniclesofelia.api.profiles.repository.UserProfileRepository;
import com.chroniclesofelia.api.progress.dto.ExerciseSubmissionResponse;
import com.chroniclesofelia.api.progress.dto.MissionProgressResponse;
import com.chroniclesofelia.api.progress.dto.ProgressSummaryResponse;
import com.chroniclesofelia.api.progress.dto.SubmitExerciseRequest;
import com.chroniclesofelia.api.progress.entity.UserExerciseAnswer;
import com.chroniclesofelia.api.progress.entity.UserLevelProgress;
import com.chroniclesofelia.api.progress.entity.UserMissionProgress;
import com.chroniclesofelia.api.progress.repository.UserExerciseAnswerRepository;
import com.chroniclesofelia.api.progress.repository.UserLevelProgressRepository;
import com.chroniclesofelia.api.progress.repository.UserMissionProgressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgressService {

    private static final String STATUS_NOT_STARTED = "NOT_STARTED";
    private static final String STATUS_IN_PROGRESS = "IN_PROGRESS";
    private static final String STATUS_COMPLETED = "COMPLETED";
    private static final int DEFAULT_TARGET_STUDY_MINUTES = 30000;

    private static final String MULTIPLE_CHOICE = "MULTIPLE_CHOICE";
    private static final String FILL_IN_THE_BLANK = "FILL_IN_THE_BLANK";
    private static final String SENTENCE_ORDERING = "SENTENCE_ORDERING";
    private static final String SHORT_WRITTEN_ANSWER = "SHORT_WRITTEN_ANSWER";

    private final UserProfileRepository userProfileRepository;
    private final MissionRepository missionRepository;
    private final ExerciseRepository exerciseRepository;
    private final ExerciseOptionRepository exerciseOptionRepository;
    private final UserLevelProgressRepository userLevelProgressRepository;
    private final UserMissionProgressRepository userMissionProgressRepository;
    private final UserExerciseAnswerRepository userExerciseAnswerRepository;

    @Transactional
    public MissionProgressResponse startMission(AppUser currentUser, Long missionId) {
        UserProfile profile = getCurrentUserProfile(currentUser);
        Mission mission = getMission(missionId);

        validateMissionMatchesProfile(profile, mission);

        UserMissionProgress missionProgress = getOrCreateMissionProgress(currentUser, mission);

        if (!STATUS_COMPLETED.equals(missionProgress.getStatus())) {
            LocalDateTime now = LocalDateTime.now();

            if (missionProgress.getStartedAt() == null) {
                missionProgress.setStartedAt(now);
            }

            missionProgress.setStatus(STATUS_IN_PROGRESS);
            missionProgress.setLastAccessedAt(now);
        }

        UserMissionProgress savedProgress = userMissionProgressRepository.save(missionProgress);

        return toMissionProgressResponse(savedProgress);
    }

    @Transactional
    public MissionProgressResponse completeMission(AppUser currentUser, Long missionId) {
        UserProfile profile = getCurrentUserProfile(currentUser);
        Mission mission = getMission(missionId);

        validateMissionMatchesProfile(profile, mission);

        UserMissionProgress missionProgress = getOrCreateMissionProgress(currentUser, mission);

        if (STATUS_COMPLETED.equals(missionProgress.getStatus())) {
            return toMissionProgressResponse(missionProgress);
        }

        LocalDateTime now = LocalDateTime.now();

        if (missionProgress.getStartedAt() == null) {
            missionProgress.setStartedAt(now);
        }

        missionProgress.setStatus(STATUS_COMPLETED);
        missionProgress.setStudyMinutesCompleted(mission.getEstimatedMinutes());
        missionProgress.setXpEarned(mission.getXpReward());
        missionProgress.setCompletedAt(now);
        missionProgress.setLastAccessedAt(now);

        UserMissionProgress savedMissionProgress = userMissionProgressRepository.save(missionProgress);

        UserLevelProgress levelProgress = getOrCreateLevelProgress(currentUser, profile);

        levelProgress.setCompletedStudyMinutes(
                levelProgress.getCompletedStudyMinutes() + mission.getEstimatedMinutes()
        );

        levelProgress.setTotalXp(
                levelProgress.getTotalXp() + mission.getXpReward()
        );

        levelProgress.setMissionsCompletedCount(
                levelProgress.getMissionsCompletedCount() + 1
        );

        userLevelProgressRepository.save(levelProgress);

        return toMissionProgressResponse(savedMissionProgress);
    }

    @Transactional
    public ExerciseSubmissionResponse submitExercise(
            AppUser currentUser,
            Long exerciseId,
            SubmitExerciseRequest request
    ) {
        UserProfile profile = getCurrentUserProfile(currentUser);

        Exercise exercise = exerciseRepository.findActiveDetailedById(exerciseId)
                .orElseThrow(() -> new IllegalArgumentException("Exercise was not found"));

        validateMissionMatchesProfile(profile, exercise.getMission());

        Boolean isCorrect = evaluateAnswer(exercise, request);

        boolean alreadyAnsweredCorrectly = userExerciseAnswerRepository
                .existsByUserIdAndExerciseIdAndIsCorrectTrue(
                        currentUser.getId(),
                        exercise.getId()
                );

        Integer xpEarned = calculateExerciseXp(
                exercise,
                isCorrect,
                alreadyAnsweredCorrectly
        );

        long previousAttempts = userExerciseAnswerRepository
                .countByUserIdAndExerciseId(
                        currentUser.getId(),
                        exercise.getId()
                );

        UserExerciseAnswer answer = UserExerciseAnswer.builder()
                .user(currentUser)
                .exercise(exercise)
                .selectedOption(getSelectedOptionOrNull(exercise, request))
                .textAnswer(cleanTextAnswer(request.textAnswer()))
                .isCorrect(isCorrect)
                .xpEarned(xpEarned)
                .attemptNumber((int) previousAttempts + 1)
                .build();

        UserExerciseAnswer savedAnswer = userExerciseAnswerRepository.save(answer);

        updateMissionAccess(currentUser, exercise.getMission());

        if (xpEarned > 0) {
            UserLevelProgress levelProgress = getOrCreateLevelProgress(currentUser, profile);

            levelProgress.setTotalXp(
                    levelProgress.getTotalXp() + xpEarned
            );

            userLevelProgressRepository.save(levelProgress);
        }

        return new ExerciseSubmissionResponse(
                savedAnswer.getId(),
                exercise.getId(),
                exercise.getExerciseType(),
                isCorrect,
                xpEarned,
                savedAnswer.getAttemptNumber(),
                buildFeedback(exercise, isCorrect, alreadyAnsweredCorrectly, xpEarned),
                exercise.getCorrectAnswer()
        );
    }

    @Transactional
    public ProgressSummaryResponse getCurrentUserProgress(AppUser currentUser) {
        UserProfile profile = getCurrentUserProfile(currentUser);

        UserLevelProgress levelProgress = getOrCreateLevelProgress(currentUser, profile);
        UserLevelProgress savedLevelProgress = userLevelProgressRepository.save(levelProgress);

        List<MissionProgressResponse> missions = userMissionProgressRepository
                .findDetailedByUserId(currentUser.getId())
                .stream()
                .map(this::toMissionProgressResponse)
                .toList();

        return new ProgressSummaryResponse(
                currentUser.getId(),
                profile.getEnglishLevel().getName(),
                profile.getEnglishLevel().getCode(),
                savedLevelProgress.getTargetStudyMinutes(),
                savedLevelProgress.getCompletedStudyMinutes(),
                calculateProgressPercentage(savedLevelProgress),
                savedLevelProgress.getTotalXp(),
                savedLevelProgress.getMissionsCompletedCount(),
                missions
        );
    }

    private Boolean evaluateAnswer(
            Exercise exercise,
            SubmitExerciseRequest request
    ) {
        String exerciseType = exercise.getExerciseType();

        if (MULTIPLE_CHOICE.equals(exerciseType) || FILL_IN_THE_BLANK.equals(exerciseType)) {
            ExerciseOption selectedOption = getRequiredSelectedOption(exercise, request);
            return Boolean.TRUE.equals(selectedOption.getIsCorrect());
        }

        if (SENTENCE_ORDERING.equals(exerciseType)) {
            String userAnswer = cleanTextAnswer(request.textAnswer());

            if (userAnswer == null) {
                throw new IllegalArgumentException("Text answer is required for this exercise");
            }

            return normalizeAnswer(userAnswer).equals(
                    normalizeAnswer(exercise.getCorrectAnswer())
            );
        }

        if (SHORT_WRITTEN_ANSWER.equals(exerciseType)) {
            String userAnswer = cleanTextAnswer(request.textAnswer());

            if (userAnswer == null) {
                throw new IllegalArgumentException("Text answer is required for this exercise");
            }

            /*
             * Para el MVP guardamos la respuesta escrita,
             * pero todavía no la calificamos automáticamente.
             * Más adelante esta parte se puede conectar con IA.
             */
            return null;
        }

        throw new IllegalArgumentException("Unsupported exercise type");
    }

    private Integer calculateExerciseXp(
            Exercise exercise,
            Boolean isCorrect,
            boolean alreadyAnsweredCorrectly
    ) {
        if (!Boolean.TRUE.equals(isCorrect)) {
            return 0;
        }

        if (alreadyAnsweredCorrectly) {
            return 0;
        }

        return exercise.getXpReward();
    }

    private ExerciseOption getRequiredSelectedOption(
            Exercise exercise,
            SubmitExerciseRequest request
    ) {
        if (request.selectedOptionId() == null) {
            throw new IllegalArgumentException("Selected option is required for this exercise");
        }

        return exerciseOptionRepository
                .findByIdAndExerciseId(
                        request.selectedOptionId(),
                        exercise.getId()
                )
                .orElseThrow(() -> new IllegalArgumentException("Selected option does not belong to this exercise"));
    }

    private ExerciseOption getSelectedOptionOrNull(
            Exercise exercise,
            SubmitExerciseRequest request
    ) {
        String exerciseType = exercise.getExerciseType();

        if (MULTIPLE_CHOICE.equals(exerciseType) || FILL_IN_THE_BLANK.equals(exerciseType)) {
            return getRequiredSelectedOption(exercise, request);
        }

        return null;
    }

    private void updateMissionAccess(
            AppUser currentUser,
            Mission mission
    ) {
        UserMissionProgress missionProgress = getOrCreateMissionProgress(currentUser, mission);
        LocalDateTime now = LocalDateTime.now();

        if (missionProgress.getStartedAt() == null) {
            missionProgress.setStartedAt(now);
        }

        if (!STATUS_COMPLETED.equals(missionProgress.getStatus())) {
            missionProgress.setStatus(STATUS_IN_PROGRESS);
        }

        missionProgress.setLastAccessedAt(now);

        userMissionProgressRepository.save(missionProgress);
    }

    private UserProfile getCurrentUserProfile(AppUser currentUser) {
        return userProfileRepository.findDetailedByUserId(currentUser.getId())
                .orElseThrow(() -> new IllegalArgumentException("Learning profile was not found"));
    }

    private Mission getMission(Long missionId) {
        return missionRepository.findActiveDetailedById(missionId)
                .orElseThrow(() -> new IllegalArgumentException("Mission was not found"));
    }

    private UserMissionProgress getOrCreateMissionProgress(
            AppUser currentUser,
            Mission mission
    ) {
        return userMissionProgressRepository
                .findByUserIdAndMissionId(currentUser.getId(), mission.getId())
                .orElseGet(() -> UserMissionProgress.builder()
                        .user(currentUser)
                        .mission(mission)
                        .status(STATUS_NOT_STARTED)
                        .studyMinutesCompleted(0)
                        .xpEarned(0)
                        .build());
    }

    private UserLevelProgress getOrCreateLevelProgress(
            AppUser currentUser,
            UserProfile profile
    ) {
        return userLevelProgressRepository
                .findByUserIdAndEnglishLevelId(
                        currentUser.getId(),
                        profile.getEnglishLevel().getId()
                )
                .orElseGet(() -> UserLevelProgress.builder()
                        .user(currentUser)
                        .englishLevel(profile.getEnglishLevel())
                        .targetStudyMinutes(DEFAULT_TARGET_STUDY_MINUTES)
                        .completedStudyMinutes(0)
                        .totalXp(0)
                        .missionsCompletedCount(0)
                        .build());
    }

    private void validateMissionMatchesProfile(
            UserProfile profile,
            Mission mission
    ) {
        String profileLevelCode = profile.getEnglishLevel().getCode();
        String profileProfessionCode = profile.getProfession().getCode();

        String missionLevelCode = mission.getEnglishLevel().getCode();
        String missionProfessionCode = mission.getProfession().getCode();

        boolean levelMatches = profileLevelCode.equalsIgnoreCase(missionLevelCode);
        boolean professionMatches = profileProfessionCode.equalsIgnoreCase(missionProfessionCode);

        if (!levelMatches || !professionMatches) {
            throw new IllegalArgumentException("Mission does not match current learning profile");
        }
    }

    private MissionProgressResponse toMissionProgressResponse(UserMissionProgress progress) {
        return new MissionProgressResponse(
                progress.getMission().getId(),
                progress.getMission().getTitle(),
                progress.getMission().getSlug(),
                progress.getStatus(),
                progress.getStudyMinutesCompleted(),
                progress.getXpEarned(),
                progress.getStartedAt(),
                progress.getCompletedAt(),
                progress.getLastAccessedAt()
        );
    }

    private Double calculateProgressPercentage(UserLevelProgress levelProgress) {
        double progress = levelProgress.getCompletedStudyMinutes() * 100.0
                / levelProgress.getTargetStudyMinutes();

        double cappedProgress = Math.min(progress, 100.0);

        return Math.round(cappedProgress * 100.0) / 100.0;
    }

    private String cleanTextAnswer(String textAnswer) {
        if (textAnswer == null || textAnswer.isBlank()) {
            return null;
        }

        return textAnswer.trim();
    }

    private String normalizeAnswer(String value) {
        if (value == null) {
            return "";
        }

        String withoutAccents = Normalizer.normalize(value, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");

        return withoutAccents
                .trim()
                .toLowerCase()
                .replaceAll("\\s+", " ")
                .replaceAll("[.!?]", "");
    }

    private String buildFeedback(
            Exercise exercise,
            Boolean isCorrect,
            boolean alreadyAnsweredCorrectly,
            Integer xpEarned
    ) {
        if (isCorrect == null) {
            return "Your answer was saved. Written feedback will be available in a future version.";
        }

        if (Boolean.TRUE.equals(isCorrect)) {
            if (alreadyAnsweredCorrectly && xpEarned == 0) {
                return "Correct again! You already earned XP for this exercise.";
            }

            return exercise.getFeedback();
        }

        return "Not yet. Review the lesson and try again.";
    }
}