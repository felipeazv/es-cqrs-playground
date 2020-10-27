package com.feazesa.projection.repository;

import com.feazesa.projection.model.LibraryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepository extends CrudRepository<LibraryEntity, Integer> {
}
