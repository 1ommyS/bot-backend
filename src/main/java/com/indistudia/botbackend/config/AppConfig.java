package com.indistudia.mediatrackerbotspring.config;

import com.indistudia.mediatrackerbotspring.integration.RestTemplateLoggingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Configuration
public class AppConfig {
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(List.of(new RestTemplateLoggingInterceptor()));
        return restTemplate;
    }


    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
