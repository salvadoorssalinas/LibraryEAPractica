package com.library.platform.upc.books.application.internal.commandservices;

import com.library.platform.upc.books.domain.model.aggregates.Book;
import com.library.platform.upc.books.domain.model.commands.CreateBookCommand;
import com.library.platform.upc.books.domain.model.commands.UpdateBookCommand;
import com.library.platform.upc.books.domain.model.valueobjects.*;
import com.library.platform.upc.books.domain.services.BookCommandService;
import com.library.platform.upc.books.infrastructure.persistence.jpa.repositories.BookRepository;
import com.library.platform.upc.books.infrastructure.persistence.jpa.repositories.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

import static java.util.Optional.empty;

@Service
public class BookCommandServiceImpl implements BookCommandService {
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;

    public BookCommandServiceImpl(BookRepository bookRepository, GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public Optional<Book> handle(CreateBookCommand command) {
        // check if genre exists
        var genre = genreRepository.findByGenre(command.genre().toUpperCase())
                .orElseThrow(() -> new IllegalArgumentException("Genre not found"));

        // check if isbn already exists
        var isbn = new Isbn(command.isbn());
        var bookExists = bookRepository.findByIsbn(isbn);
        if (bookExists.isPresent())
            throw new IllegalArgumentException("Book already exists");

        var title = new Title(command.title());
        var author = new Author(command.author());
        var publishedDate = new PublishedDate(command.publishedDate());
        var status = new Status(command.status().toUpperCase());
        var book = new Book(isbn, title, author, publishedDate, status, genre);
        try {
            bookRepository.save(book);
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Error saving book: " + e.getMessage());
        }
        return Optional.of(book);
    }

    @Override
    public Optional<Book> handle(UpdateBookCommand command) {
        var book = bookRepository.findById(command.id());
        if (book.isEmpty()) return Optional.empty();

        var genre = genreRepository.findByGenre(command.genre().toUpperCase());
        if (genre.isEmpty()) return Optional.of(new Book());

        // check if isbn already exists
        var isbn = new Isbn(command.isbn());
        var bookExists = bookRepository.findByIsbn(isbn);
        if (bookExists.isPresent() && !Objects.equals(bookExists.get().getId(), command.id()))
            throw new IllegalArgumentException("Book already exists");

        var title = new Title(command.title());
        var author = new Author(command.author());
        var publishedDate = new PublishedDate(command.publishedDate());
        var status = new Status(command.status().toUpperCase());
        var updatedBook = book.get();
        updatedBook.update(isbn, title, author, publishedDate, status, genre.get());
        try {
            bookRepository.save(updatedBook);
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Error updating book: " + e.getMessage());
        }
        return Optional.of(updatedBook);

    }
}
