package com.chroniclesofelia.api.learning.repository;

import com.chroniclesofelia.api.learning.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    Optional<Mission> findBySlug(String slug);

    List<Mission> findByIsActiveTrueOrderByOrderIndexAsc();

    List<Mission> findByEnglishLevelCodeAndProfessionCodeAndIsActiveTrueOrderByOrderIndexAsc(
            String englishLevelCode,
            String professionCode
    );
}