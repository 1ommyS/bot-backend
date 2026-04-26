package com.indistudia.botbackend.controller.dto;

public record CreateUserDto(
        Long telegramId,
        String username
) {
}
