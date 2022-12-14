package org.binar.movie.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Data
@Entity
public class Movie extends BaseEntity {


    private String movieTittle;
    private String description;
    private Boolean isPremiered;

    @JsonIgnore
    @ManyToMany(mappedBy = "moviesGenre")
    private Set<Genre> movies ;

//    @JsonIgnore
//    @OneToMany(mappedBy = "movies")
//    private List<Schedule> schedules = new ArrayList<>();

}
