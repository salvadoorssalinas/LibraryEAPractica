package com.library.platform.upc.books.domain.model.valueobjects;

public record Status(String status) {
    public Status {
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }
        // maximum 10 characters
        if (status.length() > 10) {
            throw new IllegalArgumentException("Status cannot be longer than 50 characters");
        }
        // must be AVAILABLE or BORROWED
        if (!status.equals("AVAILABLE") && !status.equals("BORROWED")) {
            throw new IllegalArgumentException("Status must be AVAILABLE or BORROWED");
        }
    }
}
