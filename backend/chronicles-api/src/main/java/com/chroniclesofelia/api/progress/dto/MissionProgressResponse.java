package com.chroniclesofelia.api.progress.dto;

import java.time.LocalDateTime;

public record MissionProgressResponse(
        Long missionId,
        String missionTitle,
        String missionSlug,
        String status,
        Integer studyMinutesCompleted,
        Integer xpEarned,
        LocalDateTime startedAt,
        LocalDateTime completedAt,
        LocalDateTime lastAccessedAt
) {
}