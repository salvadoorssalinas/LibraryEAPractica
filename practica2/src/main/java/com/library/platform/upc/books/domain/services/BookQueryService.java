package com.library.platform.upc.books.domain.services;

import com.library.platform.upc.books.domain.model.aggregates.Book;
import com.library.platform.upc.books.domain.model.queries.GetAllBooksQuery;

import java.util.List;

public interface BookQueryService {
    List<Book> handle(GetAllBooksQuery query);
}
