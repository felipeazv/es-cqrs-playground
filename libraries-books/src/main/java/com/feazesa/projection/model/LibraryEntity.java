package com.feazesa.projection.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@AllArgsConstructor
@Getter
@Entity
@NoArgsConstructor
public class LibraryEntity {

    @Id
    @GeneratedValue
    private Integer libraryId;
    @Setter
    private String name;
}
