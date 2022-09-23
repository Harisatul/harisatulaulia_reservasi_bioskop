package org.binar.challenge_4.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class SeatStudioKey implements Serializable {

    @Column(name = "studio_id")
    private Long StudioId;
    @Column(name = "seat_id")
    private Long SeatId;


}
