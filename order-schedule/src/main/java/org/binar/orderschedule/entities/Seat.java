package org.binar.orderschedule.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Seat extends BaseEntity{

    private Long seatNumber;
    private Boolean isAvailable;
    @ManyToOne
    @JoinColumn (name = "studio_id")
    @JsonIgnore
    private Studio studios;

//    @OneToMany(mappedBy = "seats")
//    private List<Order> order;

}
