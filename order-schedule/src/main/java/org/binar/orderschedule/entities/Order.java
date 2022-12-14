package org.binar.orderschedule.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
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
