package com.chroniclesofelia.api.learning.dto;

public record LessonResponse(
        Long id,
        String title,
        String content,
        String eliaTip,
        Integer estimatedMinutes,
        Integer orderIndex
) {
}