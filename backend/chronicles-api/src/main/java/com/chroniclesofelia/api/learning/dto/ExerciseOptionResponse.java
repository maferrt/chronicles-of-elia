package com.chroniclesofelia.api.learning.dto;

public record ExerciseOptionResponse(
        Long id,
        String optionText,
        Integer orderIndex
) {
}