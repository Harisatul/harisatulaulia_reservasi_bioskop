package org.binar.challenge_4.entities;


import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Seat extends BaseEntity{

    private Integer seat_id;
    private Integer seat_number;

}
