package com.chroniclesofelia.api.diagnostic.dto;

import java.util.List;

public record DiagnosticQuestionResponse(
        Long id,
        String questionText,
        String instruction,
        String skillFocus,
        String englishLevel,
        String englishLevelCode,
        Integer orderIndex,
        List<DiagnosticOptionResponse> options
) {
}