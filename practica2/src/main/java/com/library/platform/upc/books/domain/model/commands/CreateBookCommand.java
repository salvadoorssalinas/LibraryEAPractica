package com.library.platform.upc.books.domain.model.commands;

import java.util.Date;

public record CreateBookCommand(
        String isbn,
        String title,
        String author,
        Date publishedDate,
        String status,
        String genre) {}