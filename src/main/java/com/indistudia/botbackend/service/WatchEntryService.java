package com.indistudia.mediatrackerbotspring.service;

import com.indistudia.mediatrackerbotspring.domain.Media;
import com.indistudia.mediatrackerbotspring.domain.User;
import com.indistudia.mediatrackerbotspring.domain.WatchEntry;
import com.indistudia.mediatrackerbotspring.domain.vo.WatchEntryStatus;
import com.indistudia.mediatrackerbotspring.repository.WatchEntryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class WatchEntryService {
    private final WatchEntryRepository watchEntryRepository;

    @Transactional
    public void updateProgress(User user, Media media, WatchEntryStatus status) {
        WatchEntry watchEntry = watchEntryRepository
                .findByUserAndMedia(user.getId(), media.getId())
                .orElseGet(() -> {
                    WatchEntry created = new WatchEntry();
                    created.setUser(user);
                    created.setMedia(media);
                    return created;
                });

        watchEntry.setStatus(status);
        watchEntry.setStatusChangedAt(LocalDateTime.now());

        if (watchEntry.getId() == null) {
            watchEntryRepository.save(watchEntry);
        }
    }


    public List<WatchEntry> findLatest(User user, int limit) {
        List<WatchEntry> entries = watchEntryRepository.findLatestByUserId(user.getId(), limit);
        return entries == null ? Collections.emptyList() : entries;
    }

}