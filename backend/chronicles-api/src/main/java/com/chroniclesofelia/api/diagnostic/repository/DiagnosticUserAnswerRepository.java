package com.chroniclesofelia.api.diagnostic.repository;

import com.chroniclesofelia.api.diagnostic.entity.DiagnosticUserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiagnosticUserAnswerRepository extends JpaRepository<DiagnosticUserAnswer, Long> {

    List<DiagnosticUserAnswer> findByDiagnosticResultId(Long diagnosticResultId);
}