package com.chroniclesofelia.api.learning.dto;

public record VocabularyItemResponse(
        Long id,
        String term,
        String definition,
        String exampleSentence,
        Integer orderIndex
) {
}