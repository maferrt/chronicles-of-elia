package com.chroniclesofelia.api.learning.dto;

import java.util.List;

public record ExerciseResponse(
        Long id,
        String exerciseType,
        String question,
        String instruction,
        Integer xpReward,
        Integer orderIndex,
        List<ExerciseOptionResponse> options
) {
}