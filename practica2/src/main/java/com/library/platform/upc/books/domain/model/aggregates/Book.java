package com.library.platform.upc.books.domain.model.aggregates;

import com.library.platform.upc.books.domain.model.entities.Genre;
import com.library.platform.upc.books.domain.model.valueobjects.*;
import com.library.platform.upc.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

/**
 * Book entity
 * <p>
 *     Represents a book in the library.
 * </p>
 * @author Salvador Salinas
 * @version 1.0
 */
@Entity
public class Book extends AuditableAbstractAggregateRoot<Book> {
    @Embedded
    @NotNull
    @Column(unique = true)
    private Isbn isbn;

    @Embedded
    @NotNull
    private Title title;

    @Embedded
    @NotNull
    private Author author;

    @Embedded
    @NotNull
    private PublishedDate publishedDate;

    @Embedded
    @NotNull
    private Status status;

    @ManyToOne
    @JoinColumn(nullable = false, name = "genre_id")
    private Genre genre;

    public Book() {}

    public Book(Isbn isbn, Title title, Author author, PublishedDate publishedDate, Status status, Genre genre) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publishedDate = publishedDate;
        this.status = status;
        this.genre = genre;
    }

    public void update(Isbn isbn, Title title, Author author, PublishedDate publishedDate, Status status, Genre genre) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publishedDate = publishedDate;
        this.status = status;
        this.genre = genre;
    }

    public String getIsbn() {
        return isbn.isbn();
    }

    public String getTitle() {
        return title.title();
    }

    public String getAuthor() {
        return author.author();
    }

    public Date getPublishedDate() {
        return publishedDate.publishedDate();
    }

    public String getStatus() {
        return status.status();
    }

    public Genre getGenre() {
        return genre;
    }
}
