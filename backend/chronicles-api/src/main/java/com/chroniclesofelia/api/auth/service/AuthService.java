package com.chroniclesofelia.api.auth.service;

import com.chroniclesofelia.api.auth.dto.RegisterRequest;
import com.chroniclesofelia.api.auth.dto.RegisterResponse;
import com.chroniclesofelia.api.auth.entity.AppUser;
import com.chroniclesofelia.api.auth.entity.Role;
import com.chroniclesofelia.api.auth.repository.AppUserRepository;
import com.chroniclesofelia.api.auth.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AppUserRepository appUserRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterResponse register(RegisterRequest request) {
        if (appUserRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Email is already registered");
        }

        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new IllegalStateException("Default USER role was not found"));

        AppUser newUser = AppUser.builder()
                .fullName(request.fullName())
                .email(request.email().toLowerCase())
                .passwordHash(passwordEncoder.encode(request.password()))
                .role(userRole)
                .isActive(true)
                .build();

        AppUser savedUser = appUserRepository.save(newUser);

        return new RegisterResponse(
                savedUser.getId(),
                savedUser.getFullName(),
                savedUser.getEmail(),
                savedUser.getRole().getName(),
                "User registered successfully"
        );
    }
}