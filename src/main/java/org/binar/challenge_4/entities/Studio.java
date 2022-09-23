package org.binar.challenge_4.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@Entity
public class Studio extends BaseEntity{

    private String studioCode;

    @OneToMany(mappedBy = "studio")
    Set<SeatAvailable> seatAvailables;

}
