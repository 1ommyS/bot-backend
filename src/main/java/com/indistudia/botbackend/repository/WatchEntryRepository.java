package com.indistudia.botbackend.repository;

import com.indistudia.botbackend.domain.WatchEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface WatchEntryRepository extends JpaRepository<WatchEntry, Long> {
    @Query("from WatchEntry we join fetch we.media where we.user.id = :userId and we.media.id = :mediaId")
    Optional<WatchEntry> findByUserAndMedia(Long userId, Long mediaId);

    @Query("from WatchEntry we join fetch we.media where we.user.id = :userId order by we.statusChangedAt desc")
    List<WatchEntry> findLatestByUserId(Long userId);
}
