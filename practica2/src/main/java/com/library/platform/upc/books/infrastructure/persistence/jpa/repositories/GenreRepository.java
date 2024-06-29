package com.library.platform.upc.books.infrastructure.persistence.jpa.repositories;

import com.library.platform.upc.books.domain.model.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Optional<Genre> findByGenre(String genre);
}
