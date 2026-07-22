package com.chroniclesofelia.api.diagnostic.entity;

import com.chroniclesofelia.api.catalogs.entity.EnglishLevel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "diagnostic_questions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiagnosticQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * Texto principal de la pregunta.
     * Ejemplo:
     * Choose the correct sentence.
     */
    @Column(name = "question_text", nullable = false, columnDefinition = "TEXT")
    private String questionText;

    /*
     * Instrucción breve para el usuario.
     * Ejemplo:
     * Select the sentence that is grammatically correct.
     */
    @Column(length = 500)
    private String instruction;

    /*
     * Habilidad que evalúa:
     * Grammar, Vocabulary, Reading, Communication.
     */
    @Column(name = "skill_focus", nullable = false, length = 80)
    private String skillFocus;

    /*
     * Nivel al que pertenece la pregunta:
     * A1, A2 o B1.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "english_level_id", nullable = false)
    private EnglishLevel englishLevel;

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

        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}