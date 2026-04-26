package com.indistudia.botbackend.service;

import com.indistudia.botbackend.domain.Media;
import com.indistudia.botbackend.repository.MediaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class MediaService {
    private final MediaRepository mediaRepository;

    @Transactional
    public void saveAll(List<Media> medias) {
        mediaRepository.saveAll(medias);
    }

    public List<Media> findByQuery(String query) {
        return mediaRepository.findByQuery(query);
    }

    public Optional<Media> findByExternalId(String externalId) {
        return mediaRepository.findByExternalId(externalId);
    }

    public Media findByExternalIdOrThrow(String externalId) {
        return findByExternalId(externalId).orElseThrow(() -> {
            log.atError().addKeyValue("externalId", externalId).log("Media with this external id not found");
            return new RuntimeException("Media with external id " + externalId + " not found");
        });
    }
}
