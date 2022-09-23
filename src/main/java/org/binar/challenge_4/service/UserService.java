package org.binar.challenge_4.service;

import org.binar.challenge_4.entities.Users;
import org.binar.challenge_4.payload.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    List<Users> getAllUsers();
    ResponseEntity<Users> addUser(Users user);
    Users updateUser(Users newUser, String name);
    ApiResponse deleteUsers(String username);

}
