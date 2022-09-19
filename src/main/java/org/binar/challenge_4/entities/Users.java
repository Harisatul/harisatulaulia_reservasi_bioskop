package org.binar.challenge_4.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
public class Users extends BaseEntity {

    private String username;
    private String email;
    private String password;

    @Column(name = "is_activated")
    private Boolean isActived;
}
