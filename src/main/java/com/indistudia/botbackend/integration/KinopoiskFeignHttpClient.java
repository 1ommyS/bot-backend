package com.indistudia.mediatrackerbotspring.integration;

import com.indistudia.mediatrackerbotspring.integration.dto.FilmSearchResponse;
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
