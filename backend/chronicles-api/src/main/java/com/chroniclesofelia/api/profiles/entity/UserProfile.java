package com.chroniclesofelia.api.profiles.entity;

import com.chroniclesofelia.api.auth.entity.AppUser;
import com.chroniclesofelia.api.catalogs.entity.EnglishLevel;
import com.chroniclesofelia.api.catalogs.entity.Interest;
import com.chroniclesofelia.api.catalogs.entity.LearningGoal;
import com.chroniclesofelia.api.catalogs.entity.Profession;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * Cada usuario tendrá un solo perfil de aprendizaje.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private AppUser user;

    /*
     * Ejemplo:
     * DEV, CHEF, ARTIST.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profession_id", nullable = false)
    private Profession profession;

    /*
     * Ejemplo:
     * A1, A2, B1.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "english_level_id", nullable = false)
    private EnglishLevel englishLevel;

    /*
     * Texto opcional.
     * Ejemplo:
     * "I want to practice English for technical interviews."
     */
    @Column(length = 255)
    private String bio;

    /*
     * Un perfil puede tener varios intereses:
     * Books, Music, Technology, etc.
     */
    @ManyToMany
    @JoinTable(
            name = "user_profile_interests",
            joinColumns = @JoinColumn(name = "user_profile_id"),
            inverseJoinColumns = @JoinColumn(name = "interest_id")
    )
    @Builder.Default
    private Set<Interest> interests = new HashSet<>();

    /*
     * Un perfil puede tener varias metas:
     * Technical Interviews, Remote Work, Travel, etc.
     */
    @ManyToMany
    @JoinTable(
            name = "user_profile_goals",
            joinColumns = @JoinColumn(name = "user_profile_id"),
            inverseJoinColumns = @JoinColumn(name = "learning_goal_id")
    )
    @Builder.Default
    private Set<LearningGoal> learningGoals = new HashSet<>();

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