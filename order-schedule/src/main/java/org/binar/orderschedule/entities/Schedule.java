package org.binar.orderschedule.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Schedule extends BaseEntity{


    public Schedule(LocalDate premieredDate, Integer price,
                    LocalTime hourStart, LocalTime hourEnd, Movie movie, Studio studio) {
        this.premieredDate = premieredDate;
        this.price = price;
        this.hourStart = hourStart;
        this.hourEnd = hourEnd;
        this.movies = movie;
        this.studio = studio;
    }

    @Column(name = "premiere_date")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate premieredDate;

    private Integer price;

    @JsonFormat(pattern = "HH:mm")
    @Column(name = "hour_start")
    private LocalTime hourStart;

    @JsonFormat(pattern = "HH:mm")
    @Column(name = "hour_end")
    private LocalTime hourEnd;

    @ManyToOne()
    @JoinColumn(name = "movie_id")
    @JsonIgnore
    private Movie movies;

    @ManyToOne
    @JoinColumn(name = "studio_id")
    @JsonIgnore
    private Studio studio ;

//    @OneToMany(mappedBy = "schedule")
//    @JsonIgnore
//    private List<Order> order;

}
