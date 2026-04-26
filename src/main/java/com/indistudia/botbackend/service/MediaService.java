package com.indistudia.mediatrackerbotspring.service;

import com.indistudia.mediatrackerbotspring.domain.Media;
import com.indistudia.mediatrackerbotspring.repository.MediaRepository;
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
    public void save(Media media) {
        mediaRepository.save(media);
    }

    @Transactional
    public void saveAll(List<Media> medias) {
        medias.forEach(media ->
                mediaRepository.save(media)
        );
    }

    public List<Media> findByQuery(String query) {
        return mediaRepository.findByQuery(query);
    }

    public Optional<Media> findByExternalId(String externalId) {
        return mediaRepository.findByExternalId(externalId);
    }
}
