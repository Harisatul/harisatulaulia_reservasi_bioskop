package org.binar.challenge_4.service;

import org.binar.challenge_4.entities.Users;
import org.binar.challenge_4.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<Users> addUser(Users user) {
        Users save = userRepository.save(user);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Users> updateUser(Users user, String username) {
        Users users = userRepository.findUsersByUsername(username).orElseThrow();
        System.out.println(users);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
