package com.chroniclesofelia.api.progress.dto;

import java.util.List;

public record ProgressSummaryResponse(
        Long userId,
        String englishLevel,
        String englishLevelCode,
        Integer targetStudyMinutes,
        Integer completedStudyMinutes,
        Double progressPercentage,
        Integer totalXp,
        Integer missionsCompletedCount,
        List<MissionProgressResponse> missions
) {
}