package com.indistudia.botbackend.controller.dto;

import com.indistudia.botbackend.domain.vo.WatchEntryStatus;

public record UpdateWatchEntryDto(
        Long userId,
        String mediaExternalId,
        WatchEntryStatus status
) {
}
