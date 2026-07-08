package com.chroniclesofelia.api.auth.service;

import com.chroniclesofelia.api.auth.dto.LoginRequest;
import com.chroniclesofelia.api.auth.dto.LoginResponse;
import com.chroniclesofelia.api.auth.dto.RegisterRequest;
import com.chroniclesofelia.api.auth.dto.RegisterResponse;
import com.chroniclesofelia.api.auth.entity.AppUser;
import com.chroniclesofelia.api.auth.entity.Role;
import com.chroniclesofelia.api.auth.repository.AppUserRepository;
import com.chroniclesofelia.api.auth.repository.RoleRepository;
import com.chroniclesofelia.api.auth.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AppUserRepository appUserRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Transactional
    public RegisterResponse register(RegisterRequest request) {
        String normalizedEmail = request.email().trim().toLowerCase();

        if (appUserRepository.existsByEmail(normalizedEmail)) {
            throw new IllegalArgumentException("Email is already registered");
        }

        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new IllegalStateException("Default USER role was not found"));

        AppUser newUser = AppUser.builder()
                .fullName(request.fullName().trim())
                .email(normalizedEmail)
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

    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest request) {
        String normalizedEmail = request.email().trim().toLowerCase();

        AppUser user = appUserRepository.findByEmail(normalizedEmail)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        if (!Boolean.TRUE.equals(user.getIsActive())) {
            throw new IllegalArgumentException("User account is inactive");
        }

        boolean passwordMatches = passwordEncoder.matches(
                request.password(),
                user.getPasswordHash()
        );

        if (!passwordMatches) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        String token = jwtService.generateToken(user);

        return new LoginResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getRole().getName(),
                token,
                "Bearer",
                jwtService.getExpirationInSeconds()
        );
    }
}