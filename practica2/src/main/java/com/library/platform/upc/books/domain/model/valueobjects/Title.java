package com.library.platform.upc.books.domain.model.valueobjects;

public record Title(String title) {
    public Title {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        // maximum 100 characters
        if (title.length() > 100) {
            throw new IllegalArgumentException("Title cannot be longer than 100 characters");
        }
    }
}
