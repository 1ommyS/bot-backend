package com.indistudia.botbackend.service;

import com.indistudia.botbackend.domain.Media;
import com.indistudia.botbackend.domain.User;
import com.indistudia.botbackend.domain.WatchEntry;
import com.indistudia.botbackend.domain.vo.WatchEntryStatus;
import com.indistudia.botbackend.repository.WatchEntryRepository;
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
    public WatchEntry updateProgress(User user, Media media, WatchEntryStatus status) {
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

        return watchEntryRepository.save(watchEntry);
    }


    public List<WatchEntry> findLatest(User user) {
        List<WatchEntry> entries = watchEntryRepository.findLatestByUserId(user.getId());
        return entries == null ? Collections.emptyList() : entries;
    }

}
