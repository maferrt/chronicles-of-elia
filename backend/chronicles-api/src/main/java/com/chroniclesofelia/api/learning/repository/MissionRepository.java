package com.chroniclesofelia.api.learning.repository;

import com.chroniclesofelia.api.learning.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    Optional<Mission> findBySlug(String slug);

    List<Mission> findByIsActiveTrueOrderByOrderIndexAsc();

    @Query("""
            SELECT mission
            FROM Mission mission
            JOIN FETCH mission.englishLevel
            JOIN FETCH mission.profession
            LEFT JOIN FETCH mission.learningGoal
            WHERE mission.englishLevel.code = :englishLevelCode
              AND mission.profession.code = :professionCode
              AND mission.isActive = true
            ORDER BY mission.orderIndex ASC
            """)
    List<Mission> findActiveByLevelAndProfession(
            @Param("englishLevelCode") String englishLevelCode,
            @Param("professionCode") String professionCode
    );

    @Query("""
            SELECT mission
            FROM Mission mission
            JOIN FETCH mission.englishLevel
            JOIN FETCH mission.profession
            LEFT JOIN FETCH mission.learningGoal
            WHERE mission.id = :missionId
              AND mission.isActive = true
            """)
    Optional<Mission> findActiveDetailedById(@Param("missionId") Long missionId);
}