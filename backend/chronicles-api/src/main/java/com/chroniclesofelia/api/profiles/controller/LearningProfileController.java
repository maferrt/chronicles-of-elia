package com.chroniclesofelia.api.profiles.controller;

import com.chroniclesofelia.api.auth.entity.AppUser;
import com.chroniclesofelia.api.profiles.dto.LearningProfileResponse;
import com.chroniclesofelia.api.profiles.dto.UpsertLearningProfileRequest;
import com.chroniclesofelia.api.profiles.service.LearningProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profiles")
@RequiredArgsConstructor
public class LearningProfileController {

    private final LearningProfileService learningProfileService;

    @PutMapping("/me")
    public ResponseEntity<LearningProfileResponse> upsertCurrentProfile(
            Authentication authentication,
            @Valid @RequestBody UpsertLearningProfileRequest request
    ) {
        AppUser currentUser = (AppUser) authentication.getPrincipal();

        LearningProfileResponse response = learningProfileService.upsertProfile(
                currentUser,
                request
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public ResponseEntity<LearningProfileResponse> getCurrentProfile(
            Authentication authentication
    ) {
        AppUser currentUser = (AppUser) authentication.getPrincipal();

        LearningProfileResponse response = learningProfileService.getCurrentProfile(currentUser);

        return ResponseEntity.ok(response);
    }
}