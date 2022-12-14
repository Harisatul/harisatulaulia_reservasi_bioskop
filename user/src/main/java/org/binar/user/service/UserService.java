package org.binar.user.service;

import org.binar.user.dto.UserDTO;
import org.binar.user.entities.Users;
import org.binar.user.payload.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    List<Users> getAllUsers();
    ResponseEntity<Users> addUser(UserDTO users);
    Users updateUser(Users newUser, String name);
    Users getUserByUsername(String username);
    ApiResponse deleteUsers(String username);
}
