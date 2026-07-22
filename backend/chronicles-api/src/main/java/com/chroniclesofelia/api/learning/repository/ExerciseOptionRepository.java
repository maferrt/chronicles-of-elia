package com.chroniclesofelia.api.learning.repository;

import com.chroniclesofelia.api.learning.entity.ExerciseOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExerciseOptionRepository extends JpaRepository<ExerciseOption, Long> {

    List<ExerciseOption> findByExerciseIdOrderByOrderIndexAsc(Long exerciseId);

    Optional<ExerciseOption> findByIdAndExerciseId(
            Long optionId,
            Long exerciseId
    );
}