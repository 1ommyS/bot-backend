package com.indistudia.botbackend.integration;

import com.indistudia.botbackend.config.BusinessLogicProperties;
import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class KinopoiskFeignHttpRequestInterceptor {
    @Bean
    public Logger.Level kinopoiskFeignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public Logger kinopoiskFeignLogger() {
        return new KinopoiskFeignLogger();
    }

    @Bean
    public RequestInterceptor kinopoiskFeignRequestInterceptor(BusinessLogicProperties businessLogicProperties) {
        return template -> {
            template.header(
                    "Content-Type", "application/json"
            );
            template.header(
                    "X-API-KEY", businessLogicProperties.getKinopoisk().apiKey()
            );
        };
    }
}
