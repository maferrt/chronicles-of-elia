package com.chroniclesofelia.api.diagnostic.controller;

import com.chroniclesofelia.api.auth.entity.AppUser;
import com.chroniclesofelia.api.diagnostic.dto.DiagnosticQuestionResponse;
import com.chroniclesofelia.api.diagnostic.dto.DiagnosticResultResponse;
import com.chroniclesofelia.api.diagnostic.dto.SubmitDiagnosticRequest;
import com.chroniclesofelia.api.diagnostic.service.DiagnosticService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diagnostic")
@RequiredArgsConstructor
public class DiagnosticController {

    private final DiagnosticService diagnosticService;

    @GetMapping("/questions")
    public ResponseEntity<List<DiagnosticQuestionResponse>> getQuestions() {
        List<DiagnosticQuestionResponse> response = diagnosticService.getActiveQuestions();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/submit")
    public ResponseEntity<DiagnosticResultResponse> submitDiagnostic(
            @Valid @RequestBody SubmitDiagnosticRequest request,
            Authentication authentication
    ) {
        AppUser currentUser = (AppUser) authentication.getPrincipal();

        DiagnosticResultResponse response = diagnosticService.submitDiagnostic(
                currentUser,
                request
        );

        return ResponseEntity.ok(response);
    }
}