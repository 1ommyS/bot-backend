package com.indistudia.botbackend.controller.dto;

import java.time.LocalDateTime;

public record MediaDto(
        Long id,
        String sourceUrl,
        String externalId,
        String title,
        Integer year,
        String posterUrl,
        LocalDateTime updatedAt
) {
}
