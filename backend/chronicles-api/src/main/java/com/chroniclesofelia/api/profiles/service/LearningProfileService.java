package com.chroniclesofelia.api.profiles.service;

import com.chroniclesofelia.api.auth.entity.AppUser;
import com.chroniclesofelia.api.catalogs.entity.EnglishLevel;
import com.chroniclesofelia.api.catalogs.entity.Interest;
import com.chroniclesofelia.api.catalogs.entity.LearningGoal;
import com.chroniclesofelia.api.catalogs.entity.Profession;
import com.chroniclesofelia.api.catalogs.repository.EnglishLevelRepository;
import com.chroniclesofelia.api.catalogs.repository.InterestRepository;
import com.chroniclesofelia.api.catalogs.repository.LearningGoalRepository;
import com.chroniclesofelia.api.catalogs.repository.ProfessionRepository;
import com.chroniclesofelia.api.profiles.dto.LearningProfileResponse;
import com.chroniclesofelia.api.profiles.dto.UpsertLearningProfileRequest;
import com.chroniclesofelia.api.profiles.entity.UserProfile;
import com.chroniclesofelia.api.profiles.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LearningProfileService {

    private final UserProfileRepository userProfileRepository;
    private final ProfessionRepository professionRepository;
    private final EnglishLevelRepository englishLevelRepository;
    private final InterestRepository interestRepository;
    private final LearningGoalRepository learningGoalRepository;

    @Transactional
    public LearningProfileResponse upsertProfile(
            AppUser currentUser,
            UpsertLearningProfileRequest request
    ) {
        String professionCode = normalizeCode(request.professionCode());
        String englishLevelCode = normalizeCode(request.englishLevelCode());

        Set<String> interestCodes = normalizeCodes(request.interestCodes());
        Set<String> learningGoalCodes = normalizeCodes(request.learningGoalCodes());

        Profession profession = professionRepository.findByCode(professionCode)
                .orElseThrow(() -> new IllegalArgumentException("Profession code was not found"));

        EnglishLevel englishLevel = englishLevelRepository.findByCode(englishLevelCode)
                .orElseThrow(() -> new IllegalArgumentException("English level code was not found"));

        Set<Interest> interests = Set.copyOf(interestRepository.findByCodeIn(interestCodes));

        if (interests.size() != interestCodes.size()) {
            throw new IllegalArgumentException("One or more interest codes were not found");
        }

        Set<LearningGoal> learningGoals = Set.copyOf(learningGoalRepository.findByCodeIn(learningGoalCodes));

        if (learningGoals.size() != learningGoalCodes.size()) {
            throw new IllegalArgumentException("One or more learning goal codes were not found");
        }

        UserProfile profile = userProfileRepository.findByUserId(currentUser.getId())
                .orElseGet(() -> UserProfile.builder()
                        .user(currentUser)
                        .build());

        profile.setProfession(profession);
        profile.setEnglishLevel(englishLevel);
        profile.setInterests(interests);
        profile.setLearningGoals(learningGoals);
        profile.setBio(cleanBio(request.bio()));

        UserProfile savedProfile = userProfileRepository.save(profile);

        return toResponse(savedProfile);
    }

    @Transactional(readOnly = true)
    public LearningProfileResponse getCurrentProfile(AppUser currentUser) {
        UserProfile profile = userProfileRepository.findDetailedByUserId(currentUser.getId())
                .orElseThrow(() -> new IllegalArgumentException("Learning profile was not found"));

        return toResponse(profile);
    }

    private LearningProfileResponse toResponse(UserProfile profile) {
        Set<String> interests = profile.getInterests()
                .stream()
                .map(Interest::getName)
                .collect(Collectors.toSet());

        Set<String> learningGoals = profile.getLearningGoals()
                .stream()
                .map(LearningGoal::getName)
                .collect(Collectors.toSet());

        return new LearningProfileResponse(
                profile.getId(),
                profile.getProfession().getName(),
                profile.getProfession().getCode(),
                profile.getEnglishLevel().getName(),
                profile.getEnglishLevel().getCode(),
                interests,
                learningGoals,
                profile.getBio()
        );
    }

    private String normalizeCode(String code) {
        return code.trim().toUpperCase();
    }

    private Set<String> normalizeCodes(Set<String> codes) {
        return codes.stream()
                .map(this::normalizeCode)
                .collect(Collectors.toSet());
    }

    private String cleanBio(String bio) {
        if (bio == null || bio.isBlank()) {
            return null;
        }

        return bio.trim();
    }
}