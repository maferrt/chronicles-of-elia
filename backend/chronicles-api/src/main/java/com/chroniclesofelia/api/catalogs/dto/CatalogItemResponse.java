package com.chroniclesofelia.api.catalogs.dto;

public record CatalogItemResponse(
        Long id,
        String code,
        String name,
        String description
) {
}