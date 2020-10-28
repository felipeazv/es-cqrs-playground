package com.feazesa.command;

import com.feazesa.enums.Enums.LibraryStatus;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Builder
@Data
public class LibraryTemporarilyUnavailableCommand {

    @TargetAggregateIdentifier
    private final UUID aggregateId;
    private final UUID libraryId;
    private final LibraryStatus libraryStatus;
}
