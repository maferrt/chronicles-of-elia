package com.chroniclesofelia.api.auth.dto;

public record LoginResponse(
        Long id,
        String fullName,
        String email,
        String role,
        String token,
        String tokenType,
        Long expiresIn
) {
}