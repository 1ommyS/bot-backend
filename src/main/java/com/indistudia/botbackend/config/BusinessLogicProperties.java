package com.indistudia.mediatrackerbotspring.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@ConfigurationProperties("business-logic")
@ToString
@Data
public class BusinessLogicProperties {
    @NestedConfigurationProperty
    TelegramConfig telegram;
    @NestedConfigurationProperty
    KinopoiskConfig kinopoisk;
}
