package com.feazesa.aggregate;

import com.feazesa.command.RegisterBookCommand;
import com.feazesa.command.RegisterLibraryCommand;
import com.feazesa.command.UpdateLibraryCommand;
import com.feazesa.event.BookCreatedEvent;
import com.feazesa.event.LibraryCreatedEvent;
import com.feazesa.event.LibraryUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.common.Assert;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
public class Library {

    @AggregateIdentifier
    private UUID aggregateId;

    protected Library() {
        // For Axon instantiation
    }

    @CommandHandler
    public Library(RegisterLibraryCommand cmd) {
        Assert.notNull(cmd.getName(), () -> "Name should not be null");
        AggregateLifecycle.apply(new LibraryCreatedEvent(cmd.getAggregateId(), cmd.getName()));
    }

    @EventSourcingHandler
    private void createdLibrary(LibraryCreatedEvent event) {
        aggregateId = event.getAggregateId();
    }

    @CommandHandler
    public Library(UpdateLibraryCommand cmd) {
        Assert.notNull(cmd.getName(), () -> "Name should not be null");

        AggregateLifecycle.apply(new LibraryUpdatedEvent(cmd.getAggregateId(), cmd.getId(), cmd.getName()));
    }

    @EventSourcingHandler
    private void updatedLibrary(LibraryUpdatedEvent event) {
        aggregateId = event.getAggregateId();
    }

    @CommandHandler
    public Library(RegisterBookCommand cmd) {
        Assert.notNull(cmd.getLibraryId(), () -> "ID should not be null");
        Assert.notNull(cmd.getIsbn(), () -> "Book ISBN should not be null");

        AggregateLifecycle.apply(new BookCreatedEvent(
                cmd.getAggregateId(),
                cmd.getLibraryId(),
                cmd.getIsbn(),
                cmd.getTitle(),
                cmd.getAuthor())
        );
    }

    @EventSourcingHandler
    private void bookCreated(BookCreatedEvent event) {
        aggregateId = event.getAggregateId();
    }

}
