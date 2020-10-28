package com.feazesa.event;

import com.feazesa.enums.Enums.LibraryStatus;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Builder
@Data
public class LibraryStatusChangedEvent {

    @TargetAggregateIdentifier
    private final UUID aggregateId;
    private final UUID libraryId;
    private final LibraryStatus status;
}
