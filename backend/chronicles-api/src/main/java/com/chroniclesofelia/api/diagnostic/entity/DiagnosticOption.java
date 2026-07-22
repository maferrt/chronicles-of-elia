package com.chroniclesofelia.api.diagnostic.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "diagnostic_options")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiagnosticOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * Cada opción pertenece a una pregunta.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private DiagnosticQuestion question;

    /*
     * Texto visible de la opción.
     */
    @Column(name = "option_text", nullable = false, columnDefinition = "TEXT")
    private String optionText;

    /*
     * Indica si esta opción es correcta.
     */
    @Column(name = "is_correct", nullable = false)
    private Boolean isCorrect;

    /*
     * Para el MVP:
     * correcta = 1
     * incorrecta = 0
     */
    @Column(nullable = false)
    private Integer points;

    @Column(name = "order_index", nullable = false)
    private Integer orderIndex;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        if (this.isCorrect == null) {
            this.isCorrect = false;
        }

        if (this.points == null) {
            this.points = 0;
        }

        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}