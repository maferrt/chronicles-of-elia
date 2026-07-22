package com.chroniclesofelia.api.progress.controller;

import com.chroniclesofelia.api.auth.entity.AppUser;
import com.chroniclesofelia.api.progress.dto.ExerciseSubmissionResponse;
import com.chroniclesofelia.api.progress.dto.SubmitExerciseRequest;
import com.chroniclesofelia.api.progress.service.ProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exercises")
@RequiredArgsConstructor
public class ExerciseProgressController {

    private final ProgressService progressService;

    @PostMapping("/{exerciseId}/submit")
    public ResponseEntity<ExerciseSubmissionResponse> submitExercise(
            @PathVariable Long exerciseId,
            @RequestBody SubmitExerciseRequest request,
            Authentication authentication
    ) {
        AppUser currentUser = (AppUser) authentication.getPrincipal();

        ExerciseSubmissionResponse response = progressService.submitExercise(
                currentUser,
                exerciseId,
                request
        );

        return ResponseEntity.ok(response);
    }
}