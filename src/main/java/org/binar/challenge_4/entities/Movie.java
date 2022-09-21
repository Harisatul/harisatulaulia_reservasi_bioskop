package org.binar.challenge_4.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Movie extends BaseEntity{


    private String movieTittle;
    private String description;
    private Boolean isPremiered;

    @ManyToMany(mappedBy = "movies_genre")
    private Set<Genre> genre =  new HashSet<>();
}
