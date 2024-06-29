package com.library.platform.upc.books.application.internal.eventhandlers;

import com.library.platform.upc.books.domain.model.commands.CreateGenreCommand;
import com.library.platform.upc.books.domain.model.queries.GetAllGenresQuery;
import com.library.platform.upc.books.domain.services.GenreCommandService;
import com.library.platform.upc.books.domain.services.GenreQueryService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class ApplicationReadyEventHandler {
    private final GenreCommandService genreCommandService;
    private final GenreQueryService genreQueryService;

    public ApplicationReadyEventHandler(GenreCommandService genreCommandService, GenreQueryService genreQueryService) {
        this.genreCommandService = genreCommandService;
        this.genreQueryService = genreQueryService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void on() {
        var getAllGenresQuery = new GetAllGenresQuery();
        var genres = genreQueryService.handle(getAllGenresQuery);
        if (genres.isEmpty()) {
            genreCommandService.handle(new CreateGenreCommand("FICTION"));
            genreCommandService.handle(new CreateGenreCommand("NON-FICTION"));
            genreCommandService.handle(new CreateGenreCommand("SCIENCE"));
            genreCommandService.handle(new CreateGenreCommand("FANTASY"));
            genreCommandService.handle(new CreateGenreCommand("MYSTERY"));
            System.out.println("Genres created");
        }
        else {
            System.out.println("Genres already exist");
        }
    }

}
