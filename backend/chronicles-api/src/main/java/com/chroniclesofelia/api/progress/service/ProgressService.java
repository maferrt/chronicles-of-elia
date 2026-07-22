package com.chroniclesofelia.api.progress.service;

import com.chroniclesofelia.api.auth.entity.AppUser;
import com.chroniclesofelia.api.learning.entity.Mission;
import com.chroniclesofelia.api.learning.repository.MissionRepository;
import com.chroniclesofelia.api.profiles.entity.UserProfile;
import com.chroniclesofelia.api.profiles.repository.UserProfileRepository;
import com.chroniclesofelia.api.progress.dto.MissionProgressResponse;
import com.chroniclesofelia.api.progress.dto.ProgressSummaryResponse;
import com.chroniclesofelia.api.progress.entity.UserLevelProgress;
import com.chroniclesofelia.api.progress.entity.UserMissionProgress;
import com.chroniclesofelia.api.progress.repository.UserLevelProgressRepository;
import com.chroniclesofelia.api.progress.repository.UserMissionProgressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgressService {

    private static final String STATUS_NOT_STARTED = "NOT_STARTED";
    private static final String STATUS_IN_PROGRESS = "IN_PROGRESS";
    private static final String STATUS_COMPLETED = "COMPLETED";
    private static final int DEFAULT_TARGET_STUDY_MINUTES = 30000;

    private final UserProfileRepository userProfileRepository;
    private final MissionRepository missionRepository;
    private final UserLevelProgressRepository userLevelProgressRepository;
    private final UserMissionProgressRepository userMissionProgressRepository;

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
}