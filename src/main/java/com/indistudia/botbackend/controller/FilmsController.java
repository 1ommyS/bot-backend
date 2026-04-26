package com.indistudia.botbackend.controller;

import com.indistudia.botbackend.controller.dto.MediaDto;
import com.indistudia.botbackend.mappers.MediaDtoMapper;
import com.indistudia.botbackend.service.FilmsProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/films")
public class FilmsController {
    private final FilmsProxy filmsProxy;
    private final MediaDtoMapper mediaDtoMapper;

    @GetMapping("/search")
    public List<MediaDto> search(@RequestParam String query) {
        return mediaDtoMapper.toDtoList(filmsProxy.findFilms(query));
    }
}
