package com.chroniclesofelia.api.diagnostic.repository;

import com.chroniclesofelia.api.diagnostic.entity.DiagnosticQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DiagnosticQuestionRepository extends JpaRepository<DiagnosticQuestion, Long> {

    @Query("""
            SELECT question
            FROM DiagnosticQuestion question
            JOIN FETCH question.englishLevel
            WHERE question.isActive = true
            ORDER BY question.orderIndex ASC
            """)
    List<DiagnosticQuestion> findActiveQuestions();
}