package com.chroniclesofelia.api.catalogs.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "interests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Interest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * Ejemplos:
     * Books
     * Music
     * Technology
     */
    @Column(nullable = false, unique = true, length = 80)
    private String name;

    /*
     * Ejemplos:
     * BOOKS
     * MUSIC
     * TECHNOLOGY
     */
    @Column(nullable = false, unique = true, length = 50)
    private String code;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}