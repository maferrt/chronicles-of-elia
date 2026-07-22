package com.chroniclesofelia.api.learning.repository;

import com.chroniclesofelia.api.learning.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    List<Exercise> findByMissionIdAndIsActiveTrueOrderByOrderIndexAsc(Long missionId);

    List<Exercise> findByLessonIdAndIsActiveTrueOrderByOrderIndexAsc(Long lessonId);

    @Query("""
            SELECT exercise
            FROM Exercise exercise
            JOIN FETCH exercise.mission mission
            JOIN FETCH mission.englishLevel
            JOIN FETCH mission.profession
            LEFT JOIN FETCH mission.learningGoal
            LEFT JOIN FETCH exercise.lesson
            WHERE exercise.id = :exerciseId
              AND exercise.isActive = true
            """)
    Optional<Exercise> findActiveDetailedById(@Param("exerciseId") Long exerciseId);
}