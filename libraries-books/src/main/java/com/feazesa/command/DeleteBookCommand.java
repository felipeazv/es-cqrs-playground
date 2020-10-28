package com.feazesa.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Builder
@Data
public class DeleteBookCommand {

    @TargetAggregateIdentifier
    private final UUID aggregateId;
    private final UUID bookId;
}
