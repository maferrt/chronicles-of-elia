package com.chroniclesofelia.api.diagnostic.entity;

import com.chroniclesofelia.api.auth.entity.AppUser;
import com.chroniclesofelia.api.catalogs.entity.EnglishLevel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "diagnostic_results")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiagnosticResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * Usuario que hizo el diagnóstico.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    /*
     * Nivel sugerido por el diagnóstico.
     * Ejemplo: A1, A2 o B1.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "suggested_english_level_id", nullable = false)
    private EnglishLevel suggestedEnglishLevel;

    @Column(name = "total_score", nullable = false)
    private Integer totalScore;

    @Column(name = "max_score", nullable = false)
    private Integer maxScore;

    /*
     * Ejemplo:
     * A2 Wanderer
     */
    @Column(name = "result_label", nullable = false, length = 80)
    private String resultLabel;

    @Column(name = "result_description", length = 500)
    private String resultDescription;

    @Column(name = "taken_at", nullable = false)
    private LocalDateTime takenAt;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        if (this.takenAt == null) {
            this.takenAt = LocalDateTime.now();
        }

        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}