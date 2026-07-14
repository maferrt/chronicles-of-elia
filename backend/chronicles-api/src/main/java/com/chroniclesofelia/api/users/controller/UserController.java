package com.chroniclesofelia.api.users.controller;

import com.chroniclesofelia.api.auth.entity.AppUser;
import com.chroniclesofelia.api.users.dto.CurrentUserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/me")
    public ResponseEntity<CurrentUserResponse> getCurrentUser(Authentication authentication) {
        AppUser currentUser = (AppUser) authentication.getPrincipal();

        CurrentUserResponse response = new CurrentUserResponse(
                currentUser.getId(),
                currentUser.getFullName(),
                currentUser.getEmail(),
                currentUser.getRole().getName(),
                currentUser.getIsActive()
        );

        return ResponseEntity.ok(response);
    }
}