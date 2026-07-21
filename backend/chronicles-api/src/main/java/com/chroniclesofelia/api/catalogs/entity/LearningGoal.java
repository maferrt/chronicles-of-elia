package com.chroniclesofelia.api.catalogs.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "learning_goals")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LearningGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * Ejemplos:
     * Technical Interviews
     * Remote Work
     * Daily Conversation
     */
    @Column(nullable = false, unique = true, length = 100)
    private String name;

    /*
     * Ejemplos:
     * TECHNICAL_INTERVIEWS
     * REMOTE_WORK
     * DAILY_CONVERSATION
     */
    @Column(nullable = false, unique = true, length = 60)
    private String code;

    @Column(length = 255)
    private String description;

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