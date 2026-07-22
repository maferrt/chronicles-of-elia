package com.chroniclesofelia.api.profiles.dto;

import java.util.Set;

public record LearningProfileResponse(
        Long id,
        String profession,
        String professionCode,
        String englishLevel,
        String englishLevelCode,
        Set<String> interests,
        Set<String> learningGoals,
        String bio
) {
}