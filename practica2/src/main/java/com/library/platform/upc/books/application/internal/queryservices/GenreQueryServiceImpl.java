package com.library.platform.upc.books.application.internal.queryservices;

import com.library.platform.upc.books.domain.model.entities.Genre;
import com.library.platform.upc.books.domain.model.queries.GetAllGenresQuery;
import com.library.platform.upc.books.domain.services.GenreQueryService;
import com.library.platform.upc.books.infrastructure.persistence.jpa.repositories.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreQueryServiceImpl implements GenreQueryService {
    private final GenreRepository genreRepository;

    public GenreQueryServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Genre> handle(GetAllGenresQuery query) {
        return genreRepository.findAll();
    }
}
