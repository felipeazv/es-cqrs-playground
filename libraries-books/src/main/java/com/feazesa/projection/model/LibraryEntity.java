package com.feazesa.projection.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Entity
@NoArgsConstructor
public class LibraryEntity {

    @Id
    private UUID libraryId;

    @Setter
    private String name;

}
