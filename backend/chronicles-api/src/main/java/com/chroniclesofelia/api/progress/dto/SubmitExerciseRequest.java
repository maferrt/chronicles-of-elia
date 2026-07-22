package com.chroniclesofelia.api.progress.dto;

public record SubmitExerciseRequest(
        Long selectedOptionId,
        String textAnswer
) {
}