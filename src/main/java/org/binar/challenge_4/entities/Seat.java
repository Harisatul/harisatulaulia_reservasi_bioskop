package org.binar.challenge_4.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Seat extends BaseEntity{

    private Long seat_number;
    private Boolean isAvailable;
    @ManyToOne
    @JoinColumn (name = "studio_id")
    @JsonIgnore
    private Studio studios;

    @OneToMany(mappedBy = "seats")
    private List<Order> order;

}
