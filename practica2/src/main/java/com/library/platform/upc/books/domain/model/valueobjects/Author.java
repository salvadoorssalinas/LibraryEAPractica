package com.library.platform.upc.books.domain.model.valueobjects;

public record Author(String author) {
    public Author {
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("Author cannot be null or empty");
        }
        // maximum 50 characters
        if (author.length() > 50) {
            throw new IllegalArgumentException("Author cannot be longer than 50 characters");
        }
    }
}
