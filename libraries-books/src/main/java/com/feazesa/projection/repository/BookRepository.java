package com.feazesa.projection.repository;

import com.feazesa.projection.model.BookEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<BookEntity, Integer> {
    List<BookEntity> findByLibraryId(Integer libraryId);
}
