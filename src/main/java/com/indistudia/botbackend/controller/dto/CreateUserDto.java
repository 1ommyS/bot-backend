package com.indistudia.botbackend.dto;

public record CreateUserDto(
        Long telegramId,
        String username
) {
}
