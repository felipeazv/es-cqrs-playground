package com.feazesa.projection.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Data
@Entity
public class BookEntity {

    @Id
    private UUID bookId;
    private UUID libraryId;
    private String isbn;
    private String title;
    private String author;

    public BookDTO getBookDTO() {
        return new BookDTO(this.bookId, this.isbn, this.title, this.author);
    }

    @Data
    public static class BookDTO {
        private final UUID bookId;
        private final String isbn;
        private final String title;
        private final String author;
    }

    protected BookEntity() {
    }
}
