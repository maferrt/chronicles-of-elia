package com.chroniclesofelia.api.catalogs.controller;

import com.chroniclesofelia.api.catalogs.dto.CatalogItemResponse;
import com.chroniclesofelia.api.catalogs.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalogs")
@RequiredArgsConstructor
public class CatalogController {

    private final CatalogService catalogService;

    @GetMapping("/professions")
    public ResponseEntity<List<CatalogItemResponse>> getProfessions() {
        return ResponseEntity.ok(catalogService.getProfessions());
    }

    @GetMapping("/english-levels")
    public ResponseEntity<List<CatalogItemResponse>> getEnglishLevels() {
        return ResponseEntity.ok(catalogService.getEnglishLevels());
    }

    @GetMapping("/interests")
    public ResponseEntity<List<CatalogItemResponse>> getInterests() {
        return ResponseEntity.ok(catalogService.getInterests());
    }

    @GetMapping("/learning-goals")
    public ResponseEntity<List<CatalogItemResponse>> getLearningGoals() {
        return ResponseEntity.ok(catalogService.getLearningGoals());
    }
}