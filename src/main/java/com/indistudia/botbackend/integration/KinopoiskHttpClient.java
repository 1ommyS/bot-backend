package com.indistudia.botbackend.integration;

import com.indistudia.botbackend.config.BusinessLogicProperties;
import com.indistudia.botbackend.integration.dto.FilmSearchResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class KinopoiskHttpClient {
    private static final long MIN_REQUREST_INTERVAL_MS = 50L;
    private final Object lock = new Object();
    private final BusinessLogicProperties config;
    private final BusinessLogicProperties businessLogicProperties;
    private long lastRequestAtMillis;

    private final RestTemplate restTemplate;

    public Optional<FilmSearchResponse> search(String query, int page) {
        if (query == null || query.isBlank()) return Optional.empty();

        String path = config.getKinopoisk().baseUrl() + "api/v2.1/films/search-by-keyword?keyword=" + query + "&page=" + Math.max(page, 1);

        return Optional.ofNullable(executeJsonGet(path));
    }

    private FilmSearchResponse executeJsonGet(String pathWithQuery) {
        throttle();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(java.util.List.of(MediaType.APPLICATION_JSON));
        headers.set("X-API-KEY", businessLogicProperties.getKinopoisk().apiKey());

        try {
            return restTemplate.exchange(
                    pathWithQuery,
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    FilmSearchResponse.class
            ).getBody();
        } catch (HttpStatusCodeException e) {
            log.error(
                    "Kinopoisk request failed. status={}, url={}, body={}",
                    e.getStatusCode(),
                    pathWithQuery,
                    e.getResponseBodyAsString(),
                    e
            );
            throw new RuntimeException("Kinopoisk API returned an error response", e);
        } catch (RestClientException e) {
            log.error("Kinopoisk request failed before response. url={}", pathWithQuery, e);
            throw new RuntimeException("Kinopoisk API request failed", e);
        }
    }

    private void throttle() {
        synchronized (lock) {
            long now = System.currentTimeMillis();
            long waitMillis = MIN_REQUREST_INTERVAL_MS - (now - lastRequestAtMillis);
            if (waitMillis > 0) {
                try {
                    Thread.sleep(waitMillis);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("RPS exceeded");
                }
            }
            lastRequestAtMillis = System.currentTimeMillis();
        }
    }
}
