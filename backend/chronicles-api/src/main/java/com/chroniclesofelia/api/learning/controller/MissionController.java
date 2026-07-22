package com.chroniclesofelia.api.learning.controller;

import com.chroniclesofelia.api.auth.entity.AppUser;
import com.chroniclesofelia.api.learning.dto.MissionDetailResponse;
import com.chroniclesofelia.api.learning.dto.MissionSummaryResponse;
import com.chroniclesofelia.api.learning.service.LearningContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
public class MissionController {

    private final LearningContentService learningContentService;

    @GetMapping("/me")
    public ResponseEntity<List<MissionSummaryResponse>> getMyMissions(
            Authentication authentication
    ) {
        AppUser currentUser = (AppUser) authentication.getPrincipal();

        List<MissionSummaryResponse> response = learningContentService
                .getMissionsForCurrentUser(currentUser);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{missionId}")
    public ResponseEntity<MissionDetailResponse> getMissionDetail(
            @PathVariable Long missionId
    ) {
        MissionDetailResponse response = learningContentService.getMissionDetail(missionId);

        return ResponseEntity.ok(response);
    }
}