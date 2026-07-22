package com.chroniclesofelia.api.diagnostic.service;

import com.chroniclesofelia.api.auth.entity.AppUser;
import com.chroniclesofelia.api.catalogs.entity.EnglishLevel;
import com.chroniclesofelia.api.catalogs.repository.EnglishLevelRepository;
import com.chroniclesofelia.api.diagnostic.dto.*;
import com.chroniclesofelia.api.diagnostic.entity.DiagnosticOption;
import com.chroniclesofelia.api.diagnostic.entity.DiagnosticQuestion;
import com.chroniclesofelia.api.diagnostic.entity.DiagnosticResult;
import com.chroniclesofelia.api.diagnostic.entity.DiagnosticUserAnswer;
import com.chroniclesofelia.api.diagnostic.repository.DiagnosticOptionRepository;
import com.chroniclesofelia.api.diagnostic.repository.DiagnosticQuestionRepository;
import com.chroniclesofelia.api.diagnostic.repository.DiagnosticResultRepository;
import com.chroniclesofelia.api.diagnostic.repository.DiagnosticUserAnswerRepository;
import com.chroniclesofelia.api.profiles.entity.UserProfile;
import com.chroniclesofelia.api.profiles.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DiagnosticService {

    private final DiagnosticQuestionRepository diagnosticQuestionRepository;
    private final DiagnosticOptionRepository diagnosticOptionRepository;
    private final DiagnosticResultRepository diagnosticResultRepository;
    private final DiagnosticUserAnswerRepository diagnosticUserAnswerRepository;
    private final EnglishLevelRepository englishLevelRepository;
    private final UserProfileRepository userProfileRepository;

    @Transactional(readOnly = true)
    public List<DiagnosticQuestionResponse> getActiveQuestions() {
        List<DiagnosticQuestion> questions = diagnosticQuestionRepository.findActiveQuestions();

        return questions.stream()
                .map(this::toQuestionResponse)
                .toList();
    }

    @Transactional
    public DiagnosticResultResponse submitDiagnostic(
            AppUser currentUser,
            SubmitDiagnosticRequest request
    ) {
        List<DiagnosticQuestion> activeQuestions = diagnosticQuestionRepository.findActiveQuestions();

        if (activeQuestions.isEmpty()) {
            throw new IllegalStateException("No diagnostic questions are available");
        }

        Map<Long, DiagnosticQuestion> activeQuestionsById = new HashMap<>();

        for (DiagnosticQuestion question : activeQuestions) {
            activeQuestionsById.put(question.getId(), question);
        }

        List<DiagnosticSelection> selections = validateAndBuildSelections(
                request,
                activeQuestions,
                activeQuestionsById
        );

        int totalScore = selections.stream()
                .mapToInt(selection -> selection.selectedOption().getPoints())
                .sum();

        int maxScore = calculateMaxScore(activeQuestions);

        String suggestedLevelCode = calculateSuggestedLevelCode(totalScore);

        EnglishLevel suggestedLevel = englishLevelRepository.findByCode(suggestedLevelCode)
                .orElseThrow(() -> new IllegalStateException("Suggested English level was not found"));

        DiagnosticResult result = DiagnosticResult.builder()
                .user(currentUser)
                .suggestedEnglishLevel(suggestedLevel)
                .totalScore(totalScore)
                .maxScore(maxScore)
                .resultLabel(buildResultLabel(suggestedLevel))
                .resultDescription(buildResultDescription(suggestedLevel.getCode()))
                .build();

        DiagnosticResult savedResult = diagnosticResultRepository.save(result);

        List<DiagnosticUserAnswer> userAnswers = selections.stream()
                .map(selection -> DiagnosticUserAnswer.builder()
                        .diagnosticResult(savedResult)
                        .question(selection.question())
                        .selectedOption(selection.selectedOption())
                        .isCorrect(selection.selectedOption().getIsCorrect())
                        .pointsEarned(selection.selectedOption().getPoints())
                        .build())
                .toList();

        diagnosticUserAnswerRepository.saveAll(userAnswers);

        boolean profileLevelUpdated = false;

        if (Boolean.TRUE.equals(request.updateProfileLevel())) {
            updateUserProfileLevel(currentUser, suggestedLevel);
            profileLevelUpdated = true;
        }

        return new DiagnosticResultResponse(
                savedResult.getId(),
                savedResult.getTotalScore(),
                savedResult.getMaxScore(),
                suggestedLevel.getName(),
                suggestedLevel.getCode(),
                savedResult.getResultLabel(),
                savedResult.getResultDescription(),
                profileLevelUpdated,
                savedResult.getTakenAt()
        );
    }

    private DiagnosticQuestionResponse toQuestionResponse(DiagnosticQuestion question) {
        List<DiagnosticOptionResponse> options = diagnosticOptionRepository
                .findByQuestionIdOrderByOrderIndexAsc(question.getId())
                .stream()
                .map(this::toOptionResponse)
                .toList();

        return new DiagnosticQuestionResponse(
                question.getId(),
                question.getQuestionText(),
                question.getInstruction(),
                question.getSkillFocus(),
                question.getEnglishLevel().getName(),
                question.getEnglishLevel().getCode(),
                question.getOrderIndex(),
                options
        );
    }

    private DiagnosticOptionResponse toOptionResponse(DiagnosticOption option) {
        return new DiagnosticOptionResponse(
                option.getId(),
                option.getOptionText(),
                option.getOrderIndex()
        );
    }

    private List<DiagnosticSelection> validateAndBuildSelections(
            SubmitDiagnosticRequest request,
            List<DiagnosticQuestion> activeQuestions,
            Map<Long, DiagnosticQuestion> activeQuestionsById
    ) {
        Set<Long> submittedQuestionIds = new HashSet<>();
        List<DiagnosticSelection> selections = new ArrayList<>();

        for (DiagnosticAnswerRequest answer : request.answers()) {
            boolean isNewQuestion = submittedQuestionIds.add(answer.questionId());

            if (!isNewQuestion) {
                throw new IllegalArgumentException("Each diagnostic question can only be answered once");
            }

            DiagnosticQuestion question = activeQuestionsById.get(answer.questionId());

            if (question == null) {
                throw new IllegalArgumentException("Question does not belong to the active diagnostic");
            }

            DiagnosticOption selectedOption = diagnosticOptionRepository
                    .findByIdAndQuestionId(
                            answer.selectedOptionId(),
                            answer.questionId()
                    )
                    .orElseThrow(() -> new IllegalArgumentException("Selected option does not belong to this question"));

            selections.add(new DiagnosticSelection(question, selectedOption));
        }

        if (submittedQuestionIds.size() != activeQuestions.size()) {
            throw new IllegalArgumentException("All diagnostic questions must be answered");
        }

        return selections;
    }

    private int calculateMaxScore(List<DiagnosticQuestion> questions) {
        int maxScore = 0;

        for (DiagnosticQuestion question : questions) {
            int questionMaxScore = diagnosticOptionRepository
                    .findByQuestionIdOrderByOrderIndexAsc(question.getId())
                    .stream()
                    .mapToInt(DiagnosticOption::getPoints)
                    .max()
                    .orElse(0);

            maxScore += questionMaxScore;
        }

        return maxScore;
    }

    private String calculateSuggestedLevelCode(Integer totalScore) {
        if (totalScore <= 3) {
            return "A1";
        }

        if (totalScore <= 6) {
            return "A2";
        }

        return "B1";
    }

    private String buildResultLabel(EnglishLevel suggestedLevel) {
        return suggestedLevel.getCode() + " " + suggestedLevel.getName();
    }

    private String buildResultDescription(String suggestedLevelCode) {
        return switch (suggestedLevelCode) {
            case "A1" -> "You can start with basic English foundations: simple phrases, personal information and everyday vocabulary.";
            case "A2" -> "You can start with simple professional communication: routines, requests, descriptions and basic work situations.";
            case "B1" -> "You can start with more independent communication: experiences, explanations, opinions and professional problem-solving.";
            default -> "Your result was calculated successfully.";
        };
    }

    private void updateUserProfileLevel(
            AppUser currentUser,
            EnglishLevel suggestedLevel
    ) {
        UserProfile profile = userProfileRepository.findByUserId(currentUser.getId())
                .orElseThrow(() -> new IllegalArgumentException("Learning profile was not found"));

        profile.setEnglishLevel(suggestedLevel);

        userProfileRepository.save(profile);
    }

    private record DiagnosticSelection(
            DiagnosticQuestion question,
            DiagnosticOption selectedOption
    ) {
    }
}