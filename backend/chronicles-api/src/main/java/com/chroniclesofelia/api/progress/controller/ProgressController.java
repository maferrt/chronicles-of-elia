package com.chroniclesofelia.api.progress.controller;

import com.chroniclesofelia.api.auth.entity.AppUser;
import com.chroniclesofelia.api.progress.dto.ProgressSummaryResponse;
import com.chroniclesofelia.api.progress.service.ProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/progress")
@RequiredArgsConstructor
public class ProgressController {

    private final ProgressService progressService;

    @GetMapping("/me")
    public ResponseEntity<ProgressSummaryResponse> getMyProgress(
            Authentication authentication
    ) {
        AppUser currentUser = (AppUser) authentication.getPrincipal();

        ProgressSummaryResponse response = progressService.getCurrentUserProgress(currentUser);

        return ResponseEntity.ok(response);
    }
}