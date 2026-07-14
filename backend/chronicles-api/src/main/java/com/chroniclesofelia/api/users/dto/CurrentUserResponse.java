package com.chroniclesofelia.api.users.dto;

public record CurrentUserResponse(
        Long id,
        String fullName,
        String email,
        String role,
        Boolean isActive
) {
}