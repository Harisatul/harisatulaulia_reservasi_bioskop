package org.binar.challenge_4.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Data
@Entity
public class Schedule extends BaseEntity{


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

    @ManyToOne
    @JoinColumn(name = "movie_id")
    @JsonIgnore
    private Movie movies ;

    @ManyToOne
    @JoinColumn(name = "studio_id")
    @JsonIgnore
    private Studio studio ;

    @OneToMany(mappedBy = "schedule")
    @JsonIgnore
    private List<Order> order;





}
