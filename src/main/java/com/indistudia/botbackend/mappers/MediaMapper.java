package com.indistudia.botbackend.mappers;


import com.indistudia.botbackend.domain.Media;
import com.indistudia.botbackend.integration.dto.FilmSearchResponse;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.firstNonBlank;


public class MediaMapper {
    private static final String SOURCE_URL = "https://kinopoiskapiunofficial.tech/api/v2.2/films/%d";

    public static List<Media> convertToDomain(FilmSearchResponse filmSearchResponse) {
        return filmSearchResponse.films().stream().map(MediaMapper::convertToDomain).toList();
    }

    public static Media convertToDomain(FilmSearchResponse.FilmDto filmDto) {
        return Media.builder()
                .externalId(filmDto.filmId().toString())
                .title(
                        firstNonBlank(filmDto.nameRu(), filmDto.nameEn(), "Без названия")
                )
                .year(Integer.parseInt(filmDto.year()))
                .posterUrl(filmDto.posterUrl() == null ? "no poster" : filmDto.posterUrl())
                .sourceUrl(String.format(SOURCE_URL, filmDto.filmId()))
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public static List<Media> convertToDomain(Optional<FilmSearchResponse> filmSearchResponse) {
        return filmSearchResponse.map(MediaMapper::convertToDomain).orElse(Collections.emptyList());
    }

}
