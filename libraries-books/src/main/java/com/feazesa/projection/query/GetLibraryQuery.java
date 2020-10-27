package com.feazesa.projection.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class GetLibraryQuery {
    private final Integer libraryId;
}
