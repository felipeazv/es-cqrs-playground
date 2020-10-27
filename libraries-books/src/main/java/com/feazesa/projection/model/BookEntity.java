package com.feazesa.projection.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@AllArgsConstructor
@Builder
@Data
@Entity
public class BookEntity {

    @Id
    @GeneratedValue
    private Integer bookId;

    private Integer libraryId;
    private String isbn;
    private String title;
    private String author;

    public BookDTO getBookDTO() {
        return new BookDTO(this.isbn, this.title, this.author);
    }

    @Data
    public static class BookDTO {
        private final String isbn;
        private final String title;
        private final String author;
    }

    protected BookEntity() {
    }
}
