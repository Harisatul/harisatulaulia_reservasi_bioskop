package org.binar.challenge_4.controller;

import org.binar.challenge_4.entities.Users;
import org.binar.challenge_4.payload.ApiResponse;
import org.binar.challenge_4.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cinema/api/v1/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<Users>> getAllUser(){
        List<Users> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Users> addUser(@RequestBody Users user){
        ResponseEntity<Users> userResponseEntity = userService.addUser(user);
        return userResponseEntity;
    }

    @PutMapping("{username}")
    public ResponseEntity<Users> updateUser(@RequestBody Users user, @PathVariable(value = "username") String username){
        Users users = userService.updateUser(user, username);
        return new ResponseEntity<>(users, HttpStatus.CREATED);
    }

    @DeleteMapping("{username}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable(value = "username") String username){
        ApiResponse apiResponse = userService.deleteUsers(username);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


}
