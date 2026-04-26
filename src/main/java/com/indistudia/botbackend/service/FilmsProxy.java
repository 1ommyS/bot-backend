package com.indistudia.mediatrackerbotspring.service;

import com.indistudia.mediatrackerbotspring.domain.Media;
import com.indistudia.mediatrackerbotspring.integration.KinopoiskFeignHttpClient;
import com.indistudia.mediatrackerbotspring.integration.dto.FilmSearchResponse;
import com.indistudia.mediatrackerbotspring.mappers.MediaMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class FilmsProxy {
    private final MediaService mediaService;
    private final KinopoiskFeignHttpClient kinopoiskFeignHttpClient;

    @Cacheable(cacheNames = "find_films_cache", key = "#query")
    public List<Media> findFilms(String query) {
        var media = mediaService.findByQuery(query);

        if (!media.isEmpty())
            return media;

        Optional<FilmSearchResponse> filmSearchResponse = kinopoiskFeignHttpClient.searchByKeyword(query, 1);
        var films = MediaMapper.convertToDomain(filmSearchResponse);
        saveFilms(films);
        return films;
    }

    private void saveFilms(List<Media> media) {
        try {
            mediaService.saveAll(media);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
