package com.indistudia.botbackend.controller.dto;

import com.indistudia.botbackend.domain.vo.WatchEntryStatus;

import java.time.LocalDateTime;

public record WatchEntryDto(
        Long id,
        Long userId,
        MediaDto media,
        WatchEntryStatus status,
        LocalDateTime statusChangedAt
) {
}
