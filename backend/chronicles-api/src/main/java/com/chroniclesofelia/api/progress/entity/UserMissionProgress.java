package com.chroniclesofelia.api.progress.entity;

import com.chroniclesofelia.api.auth.entity.AppUser;
import com.chroniclesofelia.api.learning.entity.Mission;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "user_mission_progress",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uq_user_mission_progress_user_mission",
                        columnNames = {"user_id", "mission_id"}
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserMissionProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * Usuario dueño del progreso de la misión.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    /*
     * Misión relacionada.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    /*
     * Valores permitidos por la base de datos:
     * NOT_STARTED
     * IN_PROGRESS
     * COMPLETED
     */
    @Column(nullable = false, length = 30)
    private String status;

    @Column(name = "study_minutes_completed", nullable = false)
    private Integer studyMinutesCompleted;

    @Column(name = "xp_earned", nullable = false)
    private Integer xpEarned;

    @Column(name = "started_at")
    private LocalDateTime startedAt;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    @Column(name = "last_accessed_at")
    private LocalDateTime lastAccessedAt;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        if (this.status == null) {
            this.status = "NOT_STARTED";
        }

        if (this.studyMinutesCompleted == null) {
            this.studyMinutesCompleted = 0;
        }

        if (this.xpEarned == null) {
            this.xpEarned = 0;
        }

        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}