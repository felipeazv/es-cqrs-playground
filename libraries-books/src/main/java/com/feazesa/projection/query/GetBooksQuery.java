package com.feazesa.projection.query;


import lombok.Data;

import java.util.UUID;

@Data
public class GetBooksQuery {
    private final UUID libraryId;

}
