package org.binar.challenge_4.entities;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@Entity
public class Seat extends BaseEntity{

    private Long seat_number;

    @OneToMany(mappedBy = "seat")
    Set<SeatAvailable> seatAvailables;

}
