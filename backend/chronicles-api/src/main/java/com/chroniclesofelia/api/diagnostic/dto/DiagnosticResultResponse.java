package com.chroniclesofelia.api.diagnostic.dto;

import java.time.LocalDateTime;

public record DiagnosticResultResponse(
        Long resultId,
        Integer totalScore,
        Integer maxScore,
        String suggestedEnglishLevel,
        String suggestedEnglishLevelCode,
        String resultLabel,
        String resultDescription,
        Boolean profileLevelUpdated,
        LocalDateTime takenAt
) {
}