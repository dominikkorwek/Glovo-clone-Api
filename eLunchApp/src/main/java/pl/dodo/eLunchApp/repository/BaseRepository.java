package pl.dodo.eLunchApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BaseRepository<T> extends JpaRepository<T, Long> {
    Optional<T> findByUuid(UUID uuid);
}
