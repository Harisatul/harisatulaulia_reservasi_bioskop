package org.binar.orderschedule.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.LinkedList;
import java.util.List;

@Data
@Entity
public class Studio extends BaseEntity{

    @OneToMany(mappedBy = "studios")
    @JsonIgnore
    private List<Seat> seats = new LinkedList<>();

    private String studioCode;

    @OneToMany(mappedBy = "studio")
    @JsonIgnore
    private List<Schedule> schedules = new LinkedList<>();



}