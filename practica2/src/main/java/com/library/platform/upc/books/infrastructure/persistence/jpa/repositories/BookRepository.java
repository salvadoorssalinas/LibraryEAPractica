package com.library.platform.upc.books.infrastructure.persistence.jpa.repositories;

import com.library.platform.upc.books.domain.model.aggregates.Book;
import com.library.platform.upc.books.domain.model.valueobjects.Isbn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByIsbn(Isbn isbn);
}
