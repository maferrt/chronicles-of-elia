package com.chroniclesofelia.api.progress.repository;

import com.chroniclesofelia.api.progress.entity.UserMissionProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserMissionProgressRepository extends JpaRepository<UserMissionProgress, Long> {

    Optional<UserMissionProgress> findByUserIdAndMissionId(
            Long userId,
            Long missionId
    );

    @Query("""
            SELECT progress
            FROM UserMissionProgress progress
            JOIN FETCH progress.mission mission
            WHERE progress.user.id = :userId
            ORDER BY COALESCE(
                progress.lastAccessedAt,
                progress.completedAt,
                progress.startedAt,
                progress.createdAt
            ) DESC
            """)
    List<UserMissionProgress> findDetailedByUserId(
            @Param("userId") Long userId
    );

    List<UserMissionProgress> findByUserIdAndStatus(
            Long userId,
            String status
    );

    boolean existsByUserIdAndMissionIdAndStatus(
            Long userId,
            Long missionId,
            String status
    );
}