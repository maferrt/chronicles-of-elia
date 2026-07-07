package com.chroniclesofelia.api.auth.dto;

public record RegisterResponse(
        Long id,
        String fullName,
        String email,
        String role,
        String message
) {
}