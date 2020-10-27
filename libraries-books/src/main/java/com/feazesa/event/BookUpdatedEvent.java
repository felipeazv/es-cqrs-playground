package com.feazesa.event;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Builder
@Data
public class BookUpdatedEvent {

    @TargetAggregateIdentifier
    private final UUID aggregateId;

    private final Integer libraryId;
    private final Integer bookId;
    private final String isbn;
    private final String title;
    private final String author;
}

