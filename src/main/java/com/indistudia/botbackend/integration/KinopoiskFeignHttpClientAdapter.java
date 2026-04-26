package com.indistudia.botbackend.integration;

import com.indistudia.botbackend.integration.dto.FilmSearchResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KinopoiskFeignHttpClientAdapter {
    private final KinopoiskFeignHttpClient kinopoiskFeignHttpClient;

    @RateLimiter(name = "kinopoisk", fallbackMethod = "defaultSearchByKeywordResult")
    @CircuitBreaker(name = "kinopoisk", fallbackMethod = "defaultSearchByKeywordResult")
    @Retry(name = "kinopoisk")
    public Optional<FilmSearchResponse> searchByKeyword(String keyword, int page) {
        return kinopoiskFeignHttpClient.searchByKeyword(keyword, page);
    }

    public Optional<FilmSearchResponse> defaultSearchByKeywordResult(String keyword, int page, Exception exception) {
        return Optional.empty();
    }
}
