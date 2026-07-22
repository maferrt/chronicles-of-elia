package com.chroniclesofelia.api.progress.repository;

import com.chroniclesofelia.api.progress.entity.UserMissionProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserMissionProgressRepository extends JpaRepository<UserMissionProgress, Long> {

    Optional<UserMissionProgress> findByUserIdAndMissionId(
            Long userId,
            Long missionId
    );

    List<UserMissionProgress> findByUserIdOrderByUpdatedAtDesc(
            Long userId
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