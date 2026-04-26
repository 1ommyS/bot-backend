package com.indistudia.botbackend.repository;

import com.indistudia.botbackend.domain.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MediaRepository extends JpaRepository<Media, Long> {
    Optional<Media> findByExternalId(String externalId);
    @Query(value = "from Media m where lower(m.title) like lower(concat('%', :query, '%'))")
    List<Media> findByQuery(String query);
}
