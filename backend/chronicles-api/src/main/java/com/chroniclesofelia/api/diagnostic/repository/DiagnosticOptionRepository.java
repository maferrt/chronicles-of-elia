package com.chroniclesofelia.api.diagnostic.repository;

import com.chroniclesofelia.api.diagnostic.entity.DiagnosticOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DiagnosticOptionRepository extends JpaRepository<DiagnosticOption, Long> {

    List<DiagnosticOption> findByQuestionIdOrderByOrderIndexAsc(Long questionId);

    Optional<DiagnosticOption> findByIdAndQuestionId(
            Long optionId,
            Long questionId
    );
}