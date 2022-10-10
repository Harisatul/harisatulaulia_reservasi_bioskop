package org.binar.challenge_4.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@Entity
public class Users extends BaseEntity {

    private String username;
    private String email;
    private String password;

    @Column(name = "is_activated")
    private Boolean isActived;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Order> orderSet = new LinkedList<>();
}
