package org.binar.challenge_4.service;

import org.binar.challenge_4.entities.Users;
import org.binar.challenge_4.payload.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserService {

    List<Users> getAllUsers();
    ResponseEntity<Users> addUser(Users user);
    Users updateUser(Users newUser, String name);
    ApiResponse deleteUsers(String username);

}
