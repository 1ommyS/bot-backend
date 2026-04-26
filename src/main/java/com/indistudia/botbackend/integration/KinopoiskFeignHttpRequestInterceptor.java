package com.indistudia.mediatrackerbotspring.integration;

import com.indistudia.mediatrackerbotspring.config.BusinessLogicProperties;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class KinopoiskFeignHttpRequestInterceptor {
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
