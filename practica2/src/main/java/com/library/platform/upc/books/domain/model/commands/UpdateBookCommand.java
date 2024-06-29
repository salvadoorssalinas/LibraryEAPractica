package com.library.platform.upc.books.domain.model.commands;

import java.util.Date;

public record UpdateBookCommand(
        Long id,
        String isbn,
        String title,
        String author,
        Date publishedDate,
        String status,
        String genre) {}