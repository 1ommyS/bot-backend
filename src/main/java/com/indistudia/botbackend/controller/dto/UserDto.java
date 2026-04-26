package com.indistudia.botbackend.controller.dto;

import java.time.LocalDateTime;

public record UserDto(
        Long id,
        Long telegramId,
        String username,
        LocalDateTime createdAt
) {
}
