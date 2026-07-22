package com.chroniclesofelia.api.learning.repository;

import com.chroniclesofelia.api.learning.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findByMissionIdAndIsActiveTrueOrderByOrderIndexAsc(Long missionId);
}