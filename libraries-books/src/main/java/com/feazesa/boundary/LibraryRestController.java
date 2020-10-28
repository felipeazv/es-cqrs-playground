package com.feazesa.boundary;

import com.feazesa.command.*;
import com.feazesa.projection.model.BookEntity;
import com.feazesa.projection.model.BookEntity.BookDTO;
import com.feazesa.projection.model.LibraryEntity;
import com.feazesa.projection.query.GetBooksQuery;
import com.feazesa.projection.query.GetLibraryQuery;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value = "/api/libraries", produces = MediaType.APPLICATION_JSON_VALUE)
public class LibraryRestController {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @Autowired
    public LibraryRestController(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @PostMapping
    public String addLibrary(@RequestBody LibraryEntity libraryEntity) {
        final var command = RegisterLibraryCommand.builder()
                .aggregateId(UUID.randomUUID())
                .libraryId(UUID.randomUUID())
                .name(libraryEntity.getName())
                .build();
        commandGateway.send(command);
        return "Saved";
    }

    @PutMapping("/{libraryId}")
    public String updateLibrary(@PathVariable UUID libraryId, @RequestBody LibraryEntity libraryEntity) {
        final var command = new UpdateLibraryCommand(UUID.randomUUID(), libraryId, libraryEntity.getName());
        commandGateway.send(command);
        return "Updated";
    }

    @GetMapping("/{libraryId}")
    public LibraryEntity getLibrary(@PathVariable UUID libraryId) throws InterruptedException, ExecutionException {
        return queryGateway.query(new GetLibraryQuery(libraryId), LibraryEntity.class).get();
    }

    @PostMapping("/{libraryId}/books")
    public String addBook(@PathVariable UUID libraryId, @RequestBody BookEntity book) {
        final var command = RegisterBookCommand.builder()
                .aggregateId(UUID.randomUUID())
                .libraryId(libraryId)
                .bookId(UUID.randomUUID())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .build();
        commandGateway.send(command);
        return "Added";
    }

    @PutMapping("/{libraryId}/books/{bookId}")
    public String addBook(@PathVariable UUID libraryId, @PathVariable UUID bookId, @RequestBody BookEntity book) {
        final var command = UpdateBookCommand.builder()
                .aggregateId(UUID.randomUUID())
                .libraryId(libraryId)
                .bookId(bookId)
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .build();
        commandGateway.send(command);
        return "Updated";
    }

    @GetMapping("/{libraryId}/books")
    public List<BookDTO> getBook(@PathVariable UUID libraryId) throws InterruptedException, ExecutionException {
        return queryGateway.query(new GetBooksQuery(libraryId), ResponseTypes.multipleInstancesOf(BookDTO.class)).get();
    }

    @GetMapping
    public Iterable<LibraryEntity> getLibraries() throws InterruptedException, ExecutionException {
        return queryGateway.query(GetLibraryQuery.builder().build(), ResponseTypes.multipleInstancesOf(LibraryEntity.class)).get();
    }

    @DeleteMapping("/{libraryId}/books/{bookId}")
    public String deleteBook(@PathVariable UUID bookId) {
        final var command = DeleteBookCommand.builder()
                .aggregateId(UUID.randomUUID())
                .bookId(bookId)
                .build();
        commandGateway.send(command);
        return "Deleted";
    }

    @DeleteMapping("/{libraryId}")
    public String deleteLibrary(@PathVariable UUID libraryId) {
        final var command = DeleteLibraryCommand.builder()
                .aggregateId(UUID.randomUUID())
                .libraryId(libraryId)
                .build();
        commandGateway.send(command);
        return "Deleted";
    }
}
