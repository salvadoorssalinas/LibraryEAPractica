package com.library.platform.upc.books.domain.model.valueobjects;

import java.util.Date;

public record PublishedDate(Date publishedDate) {
    public PublishedDate {
        if (publishedDate == null) {
            throw new IllegalArgumentException("Published date cannot be null");
        }
        // published date cannot be after current date
        if (publishedDate.after(new Date())) {
            throw new IllegalArgumentException("Published date cannot be after current date");
        }
    }
}
