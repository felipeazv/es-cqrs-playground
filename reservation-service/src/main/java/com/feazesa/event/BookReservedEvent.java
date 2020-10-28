package com.feazesa.event;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
public class BookReservedEvent {

    @TargetAggregateIdentifier
    private final UUID aggregateId;

    private final UUID reservationId;
    private final UUID libraryId;
    private final UUID bookId;
    private final String isbn;
    private final Instant reservedAt;
    private final LocalDate returnUntilDate;
}
