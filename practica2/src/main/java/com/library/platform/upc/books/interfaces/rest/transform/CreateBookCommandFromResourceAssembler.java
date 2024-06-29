package com.library.platform.upc.books.interfaces.rest.transform;

import com.library.platform.upc.books.domain.model.commands.CreateBookCommand;
import com.library.platform.upc.books.interfaces.rest.resources.CreateBookResource;

public class CreateBookCommandFromResourceAssembler {
    public static CreateBookCommand toCommandFromResource(CreateBookResource resource) {
        return new CreateBookCommand(
                resource.isbn(),
                resource.title(),
                resource.author(),
                resource.publishedDate(),
                resource.status(),
                resource.genre()
        );
    }
}
