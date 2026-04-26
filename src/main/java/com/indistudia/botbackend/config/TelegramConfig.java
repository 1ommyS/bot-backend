package com.indistudia.botbackend.config;

//@ConfigurationProperties("telegram.bot")
public record TelegramConfig(
        String token,
        String username
) {
}
