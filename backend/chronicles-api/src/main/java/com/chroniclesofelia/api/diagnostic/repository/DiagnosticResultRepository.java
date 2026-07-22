package com.chroniclesofelia.api.diagnostic.repository;

import com.chroniclesofelia.api.diagnostic.entity.DiagnosticResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DiagnosticResultRepository extends JpaRepository<DiagnosticResult, Long> {

    Optional<DiagnosticResult> findTopByUserIdOrderByTakenAtDesc(Long userId);

    List<DiagnosticResult> findByUserIdOrderByTakenAtDesc(Long userId);
}