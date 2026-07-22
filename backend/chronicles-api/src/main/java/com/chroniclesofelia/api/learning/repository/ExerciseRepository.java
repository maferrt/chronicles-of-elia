package com.chroniclesofelia.api.learning.repository;

import com.chroniclesofelia.api.learning.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    List<Exercise> findByMissionIdAndIsActiveTrueOrderByOrderIndexAsc(Long missionId);

    List<Exercise> findByLessonIdAndIsActiveTrueOrderByOrderIndexAsc(Long lessonId);
}