package com.chroniclesofelia.api.learning.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "exercises")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * Cada ejercicio pertenece a una misión.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    /*
     * Puede estar conectado a una lección.
     * Es opcional porque algunos ejercicios podrían ser de misión completa.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    /*
     * Ejemplos:
     * MULTIPLE_CHOICE
     * FILL_IN_THE_BLANK
     * SENTENCE_ORDERING
     * SHORT_WRITTEN_ANSWER
     */
    @Column(name = "exercise_type", nullable = false, length = 50)
    private String exerciseType;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String question;

    @Column(length = 500)
    private String instruction;

    @Column(name = "correct_answer", columnDefinition = "TEXT")
    private String correctAnswer;

    @Column(columnDefinition = "TEXT")
    private String feedback;

    @Column(name = "xp_reward", nullable = false)
    private Integer xpReward;

    @Column(name = "order_index", nullable = false)
    private Integer orderIndex;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        if (this.isActive == null) {
            this.isActive = true;
        }

        if (this.xpReward == null) {
            this.xpReward = 10;
        }

        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}