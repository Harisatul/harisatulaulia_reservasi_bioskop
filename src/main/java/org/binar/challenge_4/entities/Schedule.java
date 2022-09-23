package org.binar.challenge_4.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Data
@Entity
public class Schedule extends BaseEntity{

    @Column(name = "premiere_date")
    private LocalDate premieredDate;
    private Integer price;
    @Column(name = "hour_start")
    private LocalTime hourStart;
    @Column(name = "hour_end")
    private LocalTime hourEnd;

    @ManyToMany
    @JoinTable(name = "movie_schedule", joinColumns = @JoinColumn(name = "schedule_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private Set<Movie> movies;



}
