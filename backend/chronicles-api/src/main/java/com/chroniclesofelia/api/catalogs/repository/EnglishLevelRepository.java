package com.chroniclesofelia.api.catalogs.repository;

import com.chroniclesofelia.api.catalogs.entity.EnglishLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnglishLevelRepository extends JpaRepository<EnglishLevel, Long> {

    Optional<EnglishLevel> findByCode(String code);

    boolean existsByCode(String code);
}