package com.chroniclesofelia.api.auth.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * Nombre completo del usuario.
     * Ejemplo: María Fernanda Rodríguez Trinidad
     */
    @Column(name = "full_name", nullable = false, length = 120)
    private String fullName;

    /*
     * Correo usado para login.
     */
    @Column(nullable = false, unique = true, length = 120)
    private String email;

    /*
     * Aunque la columna se llama password,
     * aquí guardaremos la contraseña cifrada, no texto plano.
     */
    @Column(name = "password", nullable = false, length = 255)
    private String passwordHash;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    /*
     * Muchos usuarios pueden tener el mismo rol.
     * Ejemplo: muchos usuarios con rol USER.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        this.isActive = true;
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}