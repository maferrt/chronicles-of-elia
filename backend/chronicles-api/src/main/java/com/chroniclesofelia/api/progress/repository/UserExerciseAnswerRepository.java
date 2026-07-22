package com.chroniclesofelia.api.progress.repository;

import com.chroniclesofelia.api.progress.entity.UserExerciseAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserExerciseAnswerRepository extends JpaRepository<UserExerciseAnswer, Long> {

    List<UserExerciseAnswer> findByUserIdAndExerciseIdOrderByAttemptNumberDesc(
            Long userId,
            Long exerciseId
    );

    List<UserExerciseAnswer> findByUserIdOrderByAnsweredAtDesc(
            Long userId
    );

    long countByUserIdAndExerciseId(
            Long userId,
            Long exerciseId
    );

    boolean existsByUserIdAndExerciseIdAndIsCorrectTrue(
            Long userId,
            Long exerciseId
    );
}