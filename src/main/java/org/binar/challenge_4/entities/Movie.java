package org.binar.challenge_4.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
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
    @OneToMany(mappedBy = "movies")
    private List<Schedule> schedules = new ArrayList<>();

}
