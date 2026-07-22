package com.chroniclesofelia.api.learning.service;

import com.chroniclesofelia.api.auth.entity.AppUser;
import com.chroniclesofelia.api.learning.dto.*;
import com.chroniclesofelia.api.learning.entity.*;
import com.chroniclesofelia.api.learning.repository.*;
import com.chroniclesofelia.api.profiles.entity.UserProfile;
import com.chroniclesofelia.api.profiles.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LearningContentService {

    private final UserProfileRepository userProfileRepository;
    private final MissionRepository missionRepository;
    private final LessonRepository lessonRepository;
    private final VocabularyItemRepository vocabularyItemRepository;
    private final ExerciseRepository exerciseRepository;
    private final ExerciseOptionRepository exerciseOptionRepository;

    @Transactional(readOnly = true)
    public List<MissionSummaryResponse> getMissionsForCurrentUser(AppUser currentUser) {
        UserProfile profile = userProfileRepository.findDetailedByUserId(currentUser.getId())
                .orElseThrow(() -> new IllegalArgumentException("Learning profile was not found"));

        String englishLevelCode = profile.getEnglishLevel().getCode();
        String professionCode = profile.getProfession().getCode();

        List<Mission> missions = missionRepository.findActiveByLevelAndProfession(
                englishLevelCode,
                professionCode
        );

        return missions.stream()
                .map(this::toMissionSummaryResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public MissionDetailResponse getMissionDetail(Long missionId) {
        Mission mission = missionRepository.findActiveDetailedById(missionId)
                .orElseThrow(() -> new IllegalArgumentException("Mission was not found"));

        List<LessonResponse> lessons = lessonRepository
                .findByMissionIdAndIsActiveTrueOrderByOrderIndexAsc(missionId)
                .stream()
                .map(this::toLessonResponse)
                .toList();

        List<VocabularyItemResponse> vocabularyItems = vocabularyItemRepository
                .findByMissionIdAndIsActiveTrueOrderByOrderIndexAsc(missionId)
                .stream()
                .map(this::toVocabularyItemResponse)
                .toList();

        List<ExerciseResponse> exercises = exerciseRepository
                .findByMissionIdAndIsActiveTrueOrderByOrderIndexAsc(missionId)
                .stream()
                .map(this::toExerciseResponse)
                .toList();

        return new MissionDetailResponse(
                mission.getId(),
                mission.getTitle(),
                mission.getSlug(),
                mission.getDescription(),
                mission.getCommunicativeObjective(),
                mission.getEnglishLevel().getName(),
                mission.getEnglishLevel().getCode(),
                mission.getProfession().getName(),
                mission.getProfession().getCode(),
                getLearningGoalName(mission),
                getLearningGoalCode(mission),
                mission.getFunctionFocus(),
                mission.getGrammarFocus(),
                mission.getVocabularyFocus(),
                mission.getMainSkill(),
                mission.getEstimatedMinutes(),
                mission.getXpReward(),
                mission.getOrderIndex(),
                lessons,
                vocabularyItems,
                exercises
        );
    }

    private MissionSummaryResponse toMissionSummaryResponse(Mission mission) {
        return new MissionSummaryResponse(
                mission.getId(),
                mission.getTitle(),
                mission.getSlug(),
                mission.getDescription(),
                mission.getCommunicativeObjective(),
                mission.getEnglishLevel().getName(),
                mission.getEnglishLevel().getCode(),
                mission.getProfession().getName(),
                mission.getProfession().getCode(),
                getLearningGoalName(mission),
                getLearningGoalCode(mission),
                mission.getFunctionFocus(),
                mission.getGrammarFocus(),
                mission.getVocabularyFocus(),
                mission.getMainSkill(),
                mission.getEstimatedMinutes(),
                mission.getXpReward(),
                mission.getOrderIndex()
        );
    }

    private LessonResponse toLessonResponse(Lesson lesson) {
        return new LessonResponse(
                lesson.getId(),
                lesson.getTitle(),
                lesson.getContent(),
                lesson.getEliaTip(),
                lesson.getEstimatedMinutes(),
                lesson.getOrderIndex()
        );
    }

    private VocabularyItemResponse toVocabularyItemResponse(VocabularyItem vocabularyItem) {
        return new VocabularyItemResponse(
                vocabularyItem.getId(),
                vocabularyItem.getTerm(),
                vocabularyItem.getDefinition(),
                vocabularyItem.getExampleSentence(),
                vocabularyItem.getOrderIndex()
        );
    }

    private ExerciseResponse toExerciseResponse(Exercise exercise) {
        List<ExerciseOptionResponse> options = exerciseOptionRepository
                .findByExerciseIdOrderByOrderIndexAsc(exercise.getId())
                .stream()
                .map(this::toExerciseOptionResponse)
                .toList();

        return new ExerciseResponse(
                exercise.getId(),
                exercise.getExerciseType(),
                exercise.getQuestion(),
                exercise.getInstruction(),
                exercise.getXpReward(),
                exercise.getOrderIndex(),
                options
        );
    }

    private ExerciseOptionResponse toExerciseOptionResponse(ExerciseOption option) {
        return new ExerciseOptionResponse(
                option.getId(),
                option.getOptionText(),
                option.getOrderIndex()
        );
    }

    private String getLearningGoalName(Mission mission) {
        if (mission.getLearningGoal() == null) {
            return null;
        }

        return mission.getLearningGoal().getName();
    }

    private String getLearningGoalCode(Mission mission) {
        if (mission.getLearningGoal() == null) {
            return null;
        }

        return mission.getLearningGoal().getCode();
    }
}