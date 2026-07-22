package com.chroniclesofelia.api.progress.repository;

import com.chroniclesofelia.api.progress.entity.UserLevelProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserLevelProgressRepository extends JpaRepository<UserLevelProgress, Long> {

    Optional<UserLevelProgress> findByUserIdAndEnglishLevelId(
            Long userId,
            Long englishLevelId
    );

    Optional<UserLevelProgress> findByUserIdAndEnglishLevelCode(
            Long userId,
            String englishLevelCode
    );
}