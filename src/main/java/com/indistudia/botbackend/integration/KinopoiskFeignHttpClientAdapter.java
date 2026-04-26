package com.indistudia.botbackend.integration;

import com.indistudia.botbackend.integration.dto.FilmSearchResponse;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@FeignClient(
        name = "kinopoisk",
        url = "${spring.cloud.openfeign.client.config.kinopoisk.url}",
        configuration = {KinopoiskFeignHttpRequestInterceptor.class}
)
public interface KinopoiskFeignHttpClient {
    @GetMapping("/api/v2.1/films/search-by-keyword")
    Optional<FilmSearchResponse> searchByKeyword(@RequestParam(name = "keyword") String keyword, @RequestParam(name = "page") int page);
}
