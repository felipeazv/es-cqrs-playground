package com.feazesa.projection.repository;

import com.feazesa.projection.model.LibraryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LibraryProjectionRepository extends CrudRepository<LibraryEntity, UUID> {
}
