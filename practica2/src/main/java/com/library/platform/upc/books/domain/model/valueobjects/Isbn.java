package com.library.platform.upc.books.domain.model.valueobjects;

public record Isbn(String isbn) {
    public Isbn {
        if (isbn == null || isbn.isBlank()) {
            throw new IllegalArgumentException("ISBN cannot be null or empty");
        }
        // maximum 13 characters
        if (isbn.length() > 13) {
            throw new IllegalArgumentException("ISBN cannot be longer than 13 characters");
        }
    }
}
