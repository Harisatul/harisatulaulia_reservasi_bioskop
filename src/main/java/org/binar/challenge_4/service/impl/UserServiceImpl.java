package org.binar.challenge_4.service.impl;

import org.binar.challenge_4.entities.Users;
import org.binar.challenge_4.payload.ApiResponse;
import org.binar.challenge_4.repository.UserRepository;
import org.binar.challenge_4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public ResponseEntity<Users> addUser(Users user) {
        Users save = userRepository.save(user);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @Override
    public Users updateUser(Users newUser, String username) {
        Users users = userRepository.findUsersByUsername(username).orElseThrow();
        if (users != null){
            users.setUsername(newUser.getUsername());
            users.setEmail(newUser.getEmail());
            users.setPassword(newUser.getPassword());
            userRepository.save(users);
        }
        return users;
    }

    @Override
    public ApiResponse deleteUsers(String username) {
        Users users = userRepository.findUsersByUsername(username).orElseThrow();
        userRepository.delete(users);
        return new ApiResponse(true, "Successfully delete profile " + username);
    }
}
