package com.library.platform.upc.books.domain.services;

import com.library.platform.upc.books.domain.model.commands.CreateGenreCommand;
import com.library.platform.upc.books.domain.model.entities.Genre;

import java.util.Optional;

public interface GenreCommandService {
    Optional<Genre> handle(CreateGenreCommand command);
}
