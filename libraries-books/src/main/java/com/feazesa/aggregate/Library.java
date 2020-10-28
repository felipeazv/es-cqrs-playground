package com.feazesa.aggregate;

import com.feazesa.command.RegisterBookCommand;
import com.feazesa.command.RegisterLibraryCommand;
import com.feazesa.command.UpdateBookCommand;
import com.feazesa.command.UpdateLibraryCommand;
import com.feazesa.event.BookCreatedEvent;
import com.feazesa.event.BookUpdatedEvent;
import com.feazesa.event.LibraryCreatedEvent;
import com.feazesa.event.LibraryUpdatedEvent;
import lombok.extern.log4j.Log4j2;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.common.Assert;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
@Log4j2
public class Library {

    @AggregateIdentifier
    private UUID aggregateId;

    protected Library() {
        // For Axon instantiation
    }

    @CommandHandler
    public Library(RegisterLibraryCommand cmd) {
        Assert.notNull(cmd.getName(), () -> "Name should not be null");
        log.info("Command received for command {}.", cmd);
        AggregateLifecycle.apply(new LibraryCreatedEvent(cmd.getAggregateId(), cmd.getLibraryId(), cmd.getName()));
    }

    @EventSourcingHandler
    private void createdLibrary(LibraryCreatedEvent event) {
        aggregateId = event.getAggregateId();
    }

    @CommandHandler
    public Library(UpdateLibraryCommand cmd) {
        Assert.notNull(cmd.getName(), () -> "Name should not be null");
        log.info("Command received for command {}.", cmd);
        AggregateLifecycle.apply(new LibraryUpdatedEvent(cmd.getAggregateId(), cmd.getLibraryId(), cmd.getName()));
    }

    @EventSourcingHandler
    private void updatedLibrary(LibraryUpdatedEvent event) {
        aggregateId = event.getAggregateId();
        log.info("EventSourcing applied for event {}.", event);
    }

    @CommandHandler
    public Library(RegisterBookCommand cmd) {
        Assert.notNull(cmd.getLibraryId(), () -> "ID should not be null");
        Assert.notNull(cmd.getIsbn(), () -> "Book ISBN should not be null");

        log.info("Command received for command {}.", cmd);

        AggregateLifecycle.apply(BookCreatedEvent.builder()
                .aggregateId(cmd.getAggregateId())
                .bookId(cmd.getBookId())
                .author(cmd.getAuthor())
                .isbn(cmd.getIsbn())
                .libraryId(cmd.getLibraryId())
                .title(cmd.getTitle())
                .build()
        );
    }

    @EventSourcingHandler
    private void bookCreated(BookCreatedEvent event) {
        aggregateId = event.getAggregateId();
        log.info("EventSourcing applied for event {}.", event);
    }

    @CommandHandler
    public Library(UpdateBookCommand cmd) {
        Assert.notNull(cmd.getLibraryId(), () -> "ID should not be null");
        Assert.notNull(cmd.getIsbn(), () -> "Book ISBN should not be null");

        log.info("Command received for command {}.", cmd);

        AggregateLifecycle.apply(BookUpdatedEvent.builder()
                .aggregateId(cmd.getAggregateId())
                .author(cmd.getAuthor())
                .bookId(cmd.getBookId())
                .isbn(cmd.getIsbn())
                .libraryId(cmd.getLibraryId())
                .title(cmd.getTitle())
                .build()
        );
    }

    @EventSourcingHandler
    private void bookUpdated(BookUpdatedEvent event) {
        aggregateId = event.getAggregateId();
        log.info("EventSourcing applied for event {}.", event);
    }

}
