package com.indistudia.mediatrackerbotspring.config;

//@ConfigurationProperties("telegram.bot")
public record TelegramConfig(
        String token,
        String username
) {
}
