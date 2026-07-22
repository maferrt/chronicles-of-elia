package com.chroniclesofelia.api.learning.entity;

import com.chroniclesofelia.api.catalogs.entity.EnglishLevel;
import com.chroniclesofelia.api.catalogs.entity.LearningGoal;
import com.chroniclesofelia.api.catalogs.entity.Profession;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "missions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * Ejemplo:
     * Professional Introduction
     */
    @Column(nullable = false, length = 120)
    private String title;

    /*
     * Ejemplo:
     * dev-a2-professional-introduction
     */
    @Column(nullable = false, unique = true, length = 140)
    private String slug;

    @Column(length = 500)
    private String description;

    @Column(name = "communicative_objective", nullable = false, length = 500)
    private String communicativeObjective;

    /*
     * Nivel CEFR-inspired:
     * A1, A2, B1.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "english_level_id", nullable = false)
    private EnglishLevel englishLevel;

    /*
     * Path profesional:
     * DEV, CHEF, ARTIST.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profession_id", nullable = false)
    private Profession profession;

    /*
     * Meta principal:
     * TECHNICAL_INTERVIEWS, REMOTE_WORK, etc.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "learning_goal_id")
    private LearningGoal learningGoal;

    @Column(name = "function_focus", length = 150)
    private String functionFocus;

    @Column(name = "grammar_focus", length = 150)
    private String grammarFocus;

    @Column(name = "vocabulary_focus", length = 150)
    private String vocabularyFocus;

    @Column(name = "main_skill", length = 80)
    private String mainSkill;

    @Column(name = "estimated_minutes", nullable = false)
    private Integer estimatedMinutes;

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

        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}