package com.chroniclesofelia.api.diagnostic.dto;

public record DiagnosticOptionResponse(
        Long id,
        String optionText,
        Integer orderIndex
) {
}