package com.chroniclesofelia.api.progress.entity;

import com.chroniclesofelia.api.auth.entity.AppUser;
import com.chroniclesofelia.api.learning.entity.Exercise;
import com.chroniclesofelia.api.learning.entity.ExerciseOption;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_exercise_answers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserExerciseAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * Usuario que respondió.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    /*
     * Ejercicio respondido.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    /*
     * Opción seleccionada.
     * Puede ser null si el ejercicio es de respuesta escrita.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "selected_option_id")
    private ExerciseOption selectedOption;

    /*
     * Respuesta escrita.
     * Puede ser null si el ejercicio es de opción múltiple.
     */
    @Column(name = "text_answer", columnDefinition = "TEXT")
    private String textAnswer;

    @Column(name = "is_correct")
    private Boolean isCorrect;

    @Column(name = "xp_earned", nullable = false)
    private Integer xpEarned;

    @Column(name = "attempt_number", nullable = false)
    private Integer attemptNumber;

    @Column(name = "answered_at", nullable = false)
    private LocalDateTime answeredAt;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        if (this.xpEarned == null) {
            this.xpEarned = 0;
        }

        if (this.attemptNumber == null) {
            this.attemptNumber = 1;
        }

        if (this.answeredAt == null) {
            this.answeredAt = LocalDateTime.now();
        }

        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}