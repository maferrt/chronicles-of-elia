package com.chroniclesofelia.api.profiles.repository;

import com.chroniclesofelia.api.auth.entity.AppUser;
import com.chroniclesofelia.api.profiles.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    Optional<UserProfile> findByUser(AppUser user);

    boolean existsByUser(AppUser user);

    Optional<UserProfile> findByUserId(Long userId);

    @Query("""
            SELECT DISTINCT profile
            FROM UserProfile profile
            JOIN FETCH profile.user
            JOIN FETCH profile.profession
            JOIN FETCH profile.englishLevel
            LEFT JOIN FETCH profile.interests
            LEFT JOIN FETCH profile.learningGoals
            WHERE profile.user.id = :userId
            """)
    Optional<UserProfile> findDetailedByUserId(@Param("userId") Long userId);
}