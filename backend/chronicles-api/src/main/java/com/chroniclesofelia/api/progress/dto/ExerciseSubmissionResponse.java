package com.chroniclesofelia.api.progress.dto;

public record ExerciseSubmissionResponse(
        Long answerId,
        Long exerciseId,
        String exerciseType,
        Boolean isCorrect,
        Integer xpEarned,
        Integer attemptNumber,
        String feedback,
        String correctAnswer
) {
}