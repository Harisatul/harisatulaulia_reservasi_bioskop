package org.binar.challenge_4.service.impl;

import org.binar.challenge_4.entities.Users;
import org.binar.challenge_4.exception.BadRequestException;
import org.binar.challenge_4.exception.ResourceNotFoundException;
import org.binar.challenge_4.payload.ApiResponse;
import org.binar.challenge_4.repository.UserRepository;
import org.binar.challenge_4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userRepository.findUsersByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found in database "));
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        users.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(users.getUsername(), users.getPassword(), authorities);
    }


    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public ResponseEntity<Users> addUser(Users user) {
        if (userRepository.findUsersByUsername(user.getUsername()).isPresent()) {
            ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "Username is already taken");
            throw new BadRequestException(apiResponse);
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
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
    public Users getUserByUsername(String username) {
        System.out.println(username);
        Users users = userRepository.findUsersByUsername(username).orElseThrow(
                () -> new ResourceNotFoundException("User", "username", username));
        return users;
    }

    @Override
    public ApiResponse deleteUsers(String username) {
        Users users = userRepository.findUsersByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User", "id", username));
        userRepository.delete(users);
        return new ApiResponse(true, "Successfully delete profile " + username);
    }
}
