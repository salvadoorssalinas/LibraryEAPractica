package com.library.platform.upc.books.interfaces.rest;

import com.library.platform.upc.books.domain.model.queries.GetAllBooksQuery;
import com.library.platform.upc.books.domain.services.BookCommandService;
import com.library.platform.upc.books.domain.services.BookQueryService;
import com.library.platform.upc.books.interfaces.rest.resources.BookResource;
import com.library.platform.upc.books.interfaces.rest.resources.CreateBookResource;
import com.library.platform.upc.books.interfaces.rest.resources.UpdateBookResource;
import com.library.platform.upc.books.interfaces.rest.transform.BookResourceFromEntityAssembler;
import com.library.platform.upc.books.interfaces.rest.transform.CreateBookCommandFromResourceAssembler;
import com.library.platform.upc.books.interfaces.rest.transform.UpdateBookCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * BooksController
 * <p>
 *     This class represents the REST controller for the books.
 *     It is responsible for handling the HTTP requests related to the books.
 * </p>
 * @author Salvador Salinas
 * @version 1.0
 */
@RestController
@RequestMapping(value = "api/v1/books", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Books", description = "Books Endpoints")
public class BooksController {
    private final BookCommandService bookCommandService;
    private final BookQueryService bookQueryService;

    public BooksController(BookCommandService bookCommandService, BookQueryService bookQueryService) {
        this.bookCommandService = bookCommandService;
        this.bookQueryService = bookQueryService;
    }

    @Operation(summary = "Get all books", description = "Get all books created in the system")
    @GetMapping
    public ResponseEntity<List<BookResource>> getAllBooks() {
        var getAllBooksQuery = new GetAllBooksQuery();
        var books = bookQueryService.handle(getAllBooksQuery);
        var bookResources = books.stream()
                .map(BookResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(bookResources);
    }

    @Operation(summary = "Create a book", description = "Create a new book in the system")
    @PostMapping
    public ResponseEntity<BookResource> createBook(@RequestBody CreateBookResource resource) {
        var createBookCommand = CreateBookCommandFromResourceAssembler.toCommandFromResource(resource);
        var book = bookCommandService.handle(createBookCommand);
        if (book.isEmpty()) return ResponseEntity.badRequest().build();
        var bookResource = BookResourceFromEntityAssembler.toResourceFromEntity(book.get());
        return new ResponseEntity<>(bookResource, HttpStatus.CREATED);
    }

    @Operation(summary = "Update a book", description = "Update a book in the system")
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBook(@PathVariable Long id, @RequestBody UpdateBookResource resource) {
        var updateBookCommand = UpdateBookCommandFromResourceAssembler.toCommandFromResource(resource, id);
        var book = bookCommandService.handle(updateBookCommand);
        // if book wasn't found show 404
        if (book.isEmpty()) {
            var message = "Book Id not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
        // if data was incorrect show 400
        if (book.get().getId() == null) {
            var message = "Data not valid";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        var bookResource = BookResourceFromEntityAssembler.toResourceFromEntity(book.get());
        return ResponseEntity.ok(bookResource);

    }
}
