package com.chroniclesofelia.api.progress.entity;

import com.chroniclesofelia.api.auth.entity.AppUser;
import com.chroniclesofelia.api.catalogs.entity.EnglishLevel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "user_level_progress",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uq_user_level_progress_user_level",
                        columnNames = {"user_id", "english_level_id"}
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLevelProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * Usuario dueño del progreso.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    /*
     * Nivel CEFR-inspired:
     * A1, A2, B1, etc.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "english_level_id", nullable = false)
    private EnglishLevel englishLevel;

    /*
     * 500 horas = 30,000 minutos.
     */
    @Column(name = "target_study_minutes", nullable = false)
    private Integer targetStudyMinutes;

    @Column(name = "completed_study_minutes", nullable = false)
    private Integer completedStudyMinutes;

    @Column(name = "total_xp", nullable = false)
    private Integer totalXp;

    @Column(name = "missions_completed_count", nullable = false)
    private Integer missionsCompletedCount;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        if (this.targetStudyMinutes == null) {
            this.targetStudyMinutes = 30000;
        }

        if (this.completedStudyMinutes == null) {
            this.completedStudyMinutes = 0;
        }

        if (this.totalXp == null) {
            this.totalXp = 0;
        }

        if (this.missionsCompletedCount == null) {
            this.missionsCompletedCount = 0;
        }

        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}