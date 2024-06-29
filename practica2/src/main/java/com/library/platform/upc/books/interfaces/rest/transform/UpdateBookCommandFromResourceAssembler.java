package com.library.platform.upc.books.interfaces.rest.transform;

import com.library.platform.upc.books.domain.model.commands.UpdateBookCommand;
import com.library.platform.upc.books.interfaces.rest.resources.UpdateBookResource;

public class UpdateBookCommandFromResourceAssembler {
    public static UpdateBookCommand toCommandFromResource(UpdateBookResource resource, Long id) {
        return new UpdateBookCommand(
                id,
                resource.isbn(),
                resource.title(),
                resource.author(),
                resource.publishedDate(),
                resource.status(),
                resource.genre()
        );
    }
}
