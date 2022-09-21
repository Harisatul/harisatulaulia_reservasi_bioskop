package org.binar.challenge_4.entities;

import javax.persistence.*;

@Entity
public class SeatAvailable {

    @EmbeddedId
    private SeatStudioKey id;

    @ManyToOne
    @MapsId("StudioId")
    @JoinColumn(name = "studio_id")
    private Studio studio;

    @ManyToOne
    @MapsId("SeatId")
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @Column(name = "is_available")
    private Boolean isAvailable;


}
