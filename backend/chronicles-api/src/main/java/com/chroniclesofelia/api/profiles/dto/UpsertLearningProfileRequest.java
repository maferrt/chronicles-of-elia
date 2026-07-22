package com.chroniclesofelia.api.profiles.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record UpsertLearningProfileRequest(

        @NotBlank(message = "Profession code is required")
        String professionCode,

        @NotBlank(message = "English level code is required")
        String englishLevelCode,

        @NotEmpty(message = "At least one interest is required")
        Set<String> interestCodes,

        @NotEmpty(message = "At least one learning goal is required")
        Set<String> learningGoalCodes,

        @Size(max = 255, message = "Bio must be at most 255 characters")
        String bio
) {
}