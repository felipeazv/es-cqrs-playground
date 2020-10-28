package com.feazesa.event;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Builder
@Data
public class LibraryUpdatedEvent {

    @TargetAggregateIdentifier
    private final UUID aggregateId;
    private final UUID libraryId;
    private final String name;
}
