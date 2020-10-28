package com.feazesa.projection.repository;

import com.feazesa.projection.model.BookEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookProjectionRepository extends CrudRepository<BookEntity, UUID> {
    List<BookEntity> findByLibraryId(UUID libraryId);
}
