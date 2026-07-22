package com.chroniclesofelia.api.learning.repository;

import com.chroniclesofelia.api.learning.entity.VocabularyItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VocabularyItemRepository extends JpaRepository<VocabularyItem, Long> {

    List<VocabularyItem> findByMissionIdAndIsActiveTrueOrderByOrderIndexAsc(Long missionId);
}