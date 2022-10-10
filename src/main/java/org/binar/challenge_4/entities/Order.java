package org.binar.challenge_4.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "orders")
public class Order extends BaseEntity{


    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private Users user;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    @JsonIgnore
    private Schedule schedule;

    @ManyToOne()
    @JoinColumn(name = "seat_id")
    private Seat seats;


}
