package org.binar.challenge_4.controller;

import org.binar.challenge_4.entities.Users;
import org.binar.challenge_4.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cinema/api/v1/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Users> addUser(@RequestBody Users user){
        ResponseEntity<Users> userResponseEntity = userService.addUser(user);
        System.out.println("good");
        return userResponseEntity;
    }

    @PutMapping("/{username}")
    public ResponseEntity<Users> updateUser(@RequestBody Users user, @PathVariable(value = "username") String username){
        ResponseEntity<Users> userResponseEntity = userService.updateUser(user, username);
        System.out.println("good2");
        return userResponseEntity;
    }
}
