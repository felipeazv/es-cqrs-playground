package com.feazesa.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Data
public class UpdateLibraryCommand {

    @TargetAggregateIdentifier
    private final UUID aggregateId;

    private final UUID libraryId;
    private final String name;
}
