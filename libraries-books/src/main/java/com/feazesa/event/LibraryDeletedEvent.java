package com.feazesa.event;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Builder
@Data
public class LibraryDeletedEvent {

    @TargetAggregateIdentifier
    private final UUID aggregateId;
    private final UUID libraryId;
}
