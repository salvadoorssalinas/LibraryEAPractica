package com.library.platform.upc.books.application.internal.commandservices;

import com.library.platform.upc.books.domain.model.commands.CreateGenreCommand;
import com.library.platform.upc.books.domain.model.entities.Genre;
import com.library.platform.upc.books.domain.services.GenreCommandService;
import com.library.platform.upc.books.infrastructure.persistence.jpa.repositories.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GenreCommandServiceImpl implements GenreCommandService {
    private final GenreRepository genreRepository;

    public GenreCommandServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Optional<Genre> handle(CreateGenreCommand command) {
        var genre = new Genre(command.genre());
        try {
            genreRepository.save(genre);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving genre: " + e.getMessage());
        }

        return Optional.of(genre);
    }
}
