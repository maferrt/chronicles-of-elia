package com.chroniclesofelia.api.catalogs.repository;

import com.chroniclesofelia.api.catalogs.entity.LearningGoal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface LearningGoalRepository extends JpaRepository<LearningGoal, Long> {

    Optional<LearningGoal> findByCode(String code);

    List<LearningGoal> findByCodeIn(Collection<String> codes);
}