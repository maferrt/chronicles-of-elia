package com.chroniclesofelia.api.learning.dto;

public record MissionSummaryResponse(
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
        Integer orderIndex
) {
}