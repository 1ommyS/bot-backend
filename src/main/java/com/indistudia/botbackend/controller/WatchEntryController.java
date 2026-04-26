package com.indistudia.botbackend.controller;

import com.indistudia.botbackend.controller.dto.UpdateWatchEntryDto;
import com.indistudia.botbackend.controller.dto.WatchEntryDto;
import com.indistudia.botbackend.mappers.WatchEntryMapper;
import com.indistudia.botbackend.service.MediaService;
import com.indistudia.botbackend.service.UserService;
import com.indistudia.botbackend.service.WatchEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/watch-entries")
public class WatchEntryController {
    private final WatchEntryService watchEntryService;
    private final UserService userService;
    private final MediaService mediaService;
    private final WatchEntryMapper watchEntryMapper;

    @PatchMapping
    public WatchEntryDto updateProgress(@RequestBody UpdateWatchEntryDto dto) {
        var user = userService.findById(dto.userId());
        var media = mediaService.findByExternalIdOrThrow(dto.mediaExternalId());
        var watchEntry = watchEntryService.updateProgress(user, media, dto.status());

        return watchEntryMapper.toDto(watchEntry);
    }

    @GetMapping("/latest")
    public List<WatchEntryDto> findLatest(@RequestParam Long userId) {
        var user = userService.findById(userId);
        return watchEntryMapper.toDtoList(watchEntryService.findLatest(user));
    }
}
