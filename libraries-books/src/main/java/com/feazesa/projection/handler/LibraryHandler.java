package com.feazesa.projection.handler;

import com.feazesa.event.LibraryCreatedEvent;
import com.feazesa.event.LibraryUpdatedEvent;
import com.feazesa.projection.model.LibraryEntity;
import com.feazesa.projection.query.GetLibraryQuery;
import com.feazesa.projection.repository.LibraryProjectionRepository;
import lombok.extern.log4j.Log4j2;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.ResetHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class LibraryHandler {

    private final LibraryProjectionRepository libraryRepository;

    public LibraryHandler(LibraryProjectionRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    @EventHandler
    public void addLibrary(LibraryCreatedEvent event) {
        final var library = new LibraryEntity(event.getLibraryId(), event.getName());
        libraryRepository.save(library);
        log.info("Library saved {}.", event);
    }

    @EventHandler
    public void updateLibrary(LibraryUpdatedEvent event) {
        final var findLibrary = libraryRepository.findById(event.getLibraryId());
        if (findLibrary.isPresent()) {
            final var library = findLibrary.get();
            library.setName(event.getName());
            libraryRepository.save(library);
            log.info("Library updated {}.", event);
        }
    }

    @QueryHandler
    public LibraryEntity getLibrary(GetLibraryQuery query) {
        return libraryRepository.findById(query.getLibraryId()).orElseThrow();
    }

    @QueryHandler
    public Iterable<LibraryEntity> getLibraries(GetLibraryQuery query) {
        return libraryRepository.findAll();
    }

    @ResetHandler
    public void reset() {
        libraryRepository.deleteAll();
        log.info("Libraries deleted.");
    }
}
