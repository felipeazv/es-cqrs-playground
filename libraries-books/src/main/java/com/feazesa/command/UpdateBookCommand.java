package com.feazesa.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Builder
@Data
public class UpdateBookCommand {

    @TargetAggregateIdentifier
    private final UUID aggregateId;

    private final Integer bookId;
    private final Integer libraryId;
    private final String isbn;
    private final String title;
    private final String author;
}
