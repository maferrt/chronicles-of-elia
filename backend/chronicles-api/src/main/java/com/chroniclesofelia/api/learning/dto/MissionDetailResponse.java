package com.chroniclesofelia.api.learning.dto;

import java.util.List;

public record MissionDetailResponse(
        Long id,
        String title,
        String slug,
        String description,
        String communicativeObjective,
        String englishLevel,
        String englishLevelCode,
        String profession,
        String professionCode,
        String learningGoal,
        String learningGoalCode,
        String functionFocus,
        String grammarFocus,
        String vocabularyFocus,
        String mainSkill,
        Integer estimatedMinutes,
        Integer xpReward,
        Integer orderIndex,
        List<LessonResponse> lessons,
        List<VocabularyItemResponse> vocabularyItems,
        List<ExerciseResponse> exercises
) {
}