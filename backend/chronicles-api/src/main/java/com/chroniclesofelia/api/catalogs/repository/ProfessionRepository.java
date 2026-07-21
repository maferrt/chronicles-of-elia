package com.chroniclesofelia.api.catalogs.repository;

import com.chroniclesofelia.api.catalogs.entity.Profession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfessionRepository extends JpaRepository<Profession, Long> {

    Optional<Profession> findByCode(String code);

    boolean existsByCode(String code);
}