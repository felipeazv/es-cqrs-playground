package com.feazesa.event;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Data
public class LibraryUpdatedEvent {

    @TargetAggregateIdentifier
    private final UUID aggregateId;

    private final Integer libraryId;
    private final String name;
}
