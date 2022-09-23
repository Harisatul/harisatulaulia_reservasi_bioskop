package org.binar.challenge_4.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;


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
    private Movie movies ;



}
