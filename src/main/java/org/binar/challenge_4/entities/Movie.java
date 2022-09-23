package org.binar.challenge_4.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Data
@Entity
public class Movie extends BaseEntity{


    private String movieTittle;
    private String description;
    private Boolean isPremiered;

    @JsonIgnore
    @ManyToMany(mappedBy = "movies_genre")
    private Set<Genre> movies ;

    @JsonIgnore
    @ManyToMany(mappedBy = "movies")
    private Set<Schedule> schedules;

}
