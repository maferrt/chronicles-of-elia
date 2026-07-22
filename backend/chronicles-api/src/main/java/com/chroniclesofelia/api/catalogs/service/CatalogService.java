package com.chroniclesofelia.api.catalogs.service;

import com.chroniclesofelia.api.catalogs.dto.CatalogItemResponse;
import com.chroniclesofelia.api.catalogs.repository.EnglishLevelRepository;
import com.chroniclesofelia.api.catalogs.repository.InterestRepository;
import com.chroniclesofelia.api.catalogs.repository.LearningGoalRepository;
import com.chroniclesofelia.api.catalogs.repository.ProfessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogService {

    private final ProfessionRepository professionRepository;
    private final EnglishLevelRepository englishLevelRepository;
    private final InterestRepository interestRepository;
    private final LearningGoalRepository learningGoalRepository;

    public List<CatalogItemResponse> getProfessions() {
        return professionRepository.findAll()
                .stream()
                .map(profession -> new CatalogItemResponse(
                        profession.getId(),
                        profession.getCode(),
                        profession.getName(),
                        profession.getDescription()
                ))
                .toList();
    }

    public List<CatalogItemResponse> getEnglishLevels() {
        return englishLevelRepository.findAll()
                .stream()
                .map(level -> new CatalogItemResponse(
                        level.getId(),
                        level.getCode(),
                        level.getName(),
                        level.getDescription()
                ))
                .toList();
    }

    public List<CatalogItemResponse> getInterests() {
        return interestRepository.findAll()
                .stream()
                .map(interest -> new CatalogItemResponse(
                        interest.getId(),
                        interest.getCode(),
                        interest.getName(),
                        null
                ))
                .toList();
    }

    public List<CatalogItemResponse> getLearningGoals() {
        return learningGoalRepository.findAll()
                .stream()
                .map(goal -> new CatalogItemResponse(
                        goal.getId(),
                        goal.getCode(),
                        goal.getName(),
                        goal.getDescription()
                ))
                .toList();
    }
}