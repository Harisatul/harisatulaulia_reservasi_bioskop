package org.binar.challenge_4.service;

import org.binar.challenge_4.entities.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserService {

    ResponseEntity<Users> addUser(Users user);
    ResponseEntity<Users> updateUser(Users user, String name);



}
