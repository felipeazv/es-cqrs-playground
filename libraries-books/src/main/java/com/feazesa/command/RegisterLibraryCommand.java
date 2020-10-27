package com.feazesa.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Builder
@Data
public class RegisterLibraryCommand {

    @TargetAggregateIdentifier
    private final UUID aggregateId;

    private final String name;
}
