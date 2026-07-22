package com.chroniclesofelia.api.diagnostic.dto;

import jakarta.validation.constraints.NotNull;

public record DiagnosticAnswerRequest(

        @NotNull(message = "Question id is required")
        Long questionId,

        @NotNull(message = "Selected option id is required")
        Long selectedOptionId
) {
}