package com.feazesa.projection.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Builder
@Data
public class GetLibraryQuery {
    private final UUID libraryId;
}
