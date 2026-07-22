package com.chroniclesofelia.api.diagnostic.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record SubmitDiagnosticRequest(

        @NotEmpty(message = "Diagnostic answers are required")
        List<@Valid DiagnosticAnswerRequest> answers,

        Boolean updateProfileLevel
) {
}