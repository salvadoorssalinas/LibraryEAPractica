package com.library.platform.upc.books.domain.services;

import com.library.platform.upc.books.domain.model.aggregates.Book;
import com.library.platform.upc.books.domain.model.commands.CreateBookCommand;
import com.library.platform.upc.books.domain.model.commands.UpdateBookCommand;

import java.util.Optional;

public interface BookCommandService {
    Optional<Book> handle(CreateBookCommand command);
    Optional<Book> handle(UpdateBookCommand command);
}
