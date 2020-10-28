package com.feazesa.projection.model;

import com.feazesa.enums.Enums.LibraryStatus;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Getter
@Entity
@NoArgsConstructor
public class LibraryEntity {

    @Id
    private UUID libraryId;
    @Setter
    private String name;
    @Setter
    private LibraryStatus status;

}
