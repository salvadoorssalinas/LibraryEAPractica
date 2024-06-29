package com.library.platform.upc.books.domain.services;

import com.library.platform.upc.books.domain.model.entities.Genre;
import com.library.platform.upc.books.domain.model.queries.GetAllGenresQuery;

import java.util.List;

public interface GenreQueryService {
    List<Genre> handle(GetAllGenresQuery query);
}
