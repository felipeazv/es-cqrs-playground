package com.feazesa.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Builder
@Data
public class RegisterBookCommand {
    @TargetAggregateIdentifier
    private final UUID aggregateId;

    private final UUID libraryId;
    private final UUID bookId;
    private final String isbn;
    private final String title;
    private final String author;

}