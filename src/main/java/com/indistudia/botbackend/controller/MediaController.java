package com.indistudia.botbackend.controller;

import com.indistudia.botbackend.controller.dto.MediaDto;
import com.indistudia.botbackend.mappers.MediaDtoMapper;
import com.indistudia.botbackend.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/media")
public class MediaController {
    private final MediaService mediaService;
    private final MediaDtoMapper mediaDtoMapper;

    @GetMapping("/search")
    public List<MediaDto> search(@RequestParam String query) {
        return mediaDtoMapper.toDtoList(mediaService.findByQuery(query));
    }

    @GetMapping("/external/{externalId}")
    public MediaDto findByExternalId(@PathVariable String externalId) {
        return mediaDtoMapper.toDto(mediaService.findByExternalIdOrThrow(externalId));
    }
}
