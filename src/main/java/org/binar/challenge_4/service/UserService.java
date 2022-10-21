package org.binar.challenge_4.service;

import org.binar.challenge_4.dto.UserDTO;
import org.binar.challenge_4.entities.Users;
import org.binar.challenge_4.payload.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    List<Users> getAllUsers();
    ResponseEntity<Users> addUser(UserDTO users);
    Users updateUser(Users newUser, String name);
    Users getUserByUsername(String username);
    ApiResponse deleteUsers(String username);

}
