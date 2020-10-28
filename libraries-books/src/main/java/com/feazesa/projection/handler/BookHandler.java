package com.feazesa.projection.handler;

import com.feazesa.event.BookCreatedEvent;
import com.feazesa.event.BookUpdatedEvent;
import com.feazesa.projection.model.BookEntity;
import com.feazesa.projection.model.BookEntity.BookDTO;
import com.feazesa.projection.query.GetBooksQuery;
import com.feazesa.projection.repository.BookProjectionRepository;
import lombok.extern.log4j.Log4j2;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.ResetHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class BookHandler {

    private final BookProjectionRepository bookRepository;

    public BookHandler(BookProjectionRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @EventHandler
    public void addBook(BookCreatedEvent event) {
        final var book = BookEntity.builder()
                .libraryId(event.getLibraryId())
                .bookId(event.getBookId())
                .author(event.getAuthor())
                .isbn(event.getIsbn())
                .title(event.getTitle())
                .build();
        bookRepository.save(book);
        log.info("Book saved {}.", event);
    }

    @EventHandler
    public void updateBook(BookUpdatedEvent event) {
        final var findBook = bookRepository.findById(event.getBookId());
        if (findBook.isPresent()) {
            final var book = findBook.get();
            book.setAuthor(event.getAuthor());
            book.setIsbn(event.getIsbn());
            book.setTitle(event.getTitle());
            bookRepository.save(book);
            log.info("Book updated {}.", event);
        }
    }

    @QueryHandler
    public List<BookDTO> getBooks(GetBooksQuery query) {
        return bookRepository.findByLibraryId(query.getLibraryId()).stream()
                .map(BookEntity::getBookDTO).collect(Collectors.toList());
    }

    @ResetHandler
    public void reset() {
        bookRepository.deleteAll();
        log.info("Books deleted.");
    }
}
