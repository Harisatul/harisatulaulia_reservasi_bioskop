package org.binar.challenge_4.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.binar.challenge_4.dto.UserDTO;
import org.binar.challenge_4.entities.Role;
import org.binar.challenge_4.entities.Users;
import org.binar.challenge_4.exception.BadRequestException;
import org.binar.challenge_4.exception.ResourceNotFoundException;
import org.binar.challenge_4.payload.ApiResponse;
import org.binar.challenge_4.repository.RoleRepository;
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

import java.util.*;

@Service
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleRepository roleRepository;

    private final String ERROR = "ERROR : ";
    private final String ROLE_USERS = "ROLE_USERS";
    private final String ROLE_ADMIN = "ROLE_ADMIN";
    private final String INFO = "INFO  : ";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userRepository.findUsersByUsername(username)
                .orElseThrow(() -> {
                    log.error(ERROR + "User not found in database ");
                    throw new UsernameNotFoundException("User not found in database ");
                });
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        users.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new org.springframework.security.core.userdetails.User(users.getUsername(), users.getPassword(), authorities);
    }


    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public ResponseEntity<Users> addUser(UserDTO user) {
        if (userRepository.findUsersByUsername(user.getUsername()).isPresent()) {
            ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "Username is already taken");
            log.error(ERROR+ "Username is already taken");
            throw new BadRequestException(apiResponse);
        }
        if (userRepository.findUsersByEmail(user.getEmail()).isPresent()){
            ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "Email is already taken");
            log.error(ERROR+ "Email is already taken");
            throw new BadRequestException(apiResponse);
        }
        List<String> reqRole = user.getRole();
        List<Role> roles = new LinkedList<>();

        if (reqRole == null){
            Role userRole = roleRepository.findRoleByName(ROLE_USERS).orElseThrow();
            log.info(INFO + user.getUsername() + " has assigned to ROLE_USER");
            roles.add(userRole);
        }else{
            reqRole.forEach(role -> {
                switch (role){
                    case ROLE_ADMIN:
                        Role adminRole = roleRepository.findRoleByName(ROLE_ADMIN).orElseThrow(
                                () -> {
                                    log.error(ERROR+ "ROLE_ADMIN NOT FOUND");
                                    throw new ResourceNotFoundException("Role", "role", ROLE_ADMIN);
                                });
                        roles.add(adminRole);
                        log.info("Info : "+ user.getUsername() + " has assigned to ROLE_ADMIN");
                        break;
                    default:
                        Role userRole = roleRepository.findRoleByName(ROLE_USERS).orElseThrow(
                                () -> {
                                    log.error("ERROR : "+ "ROLE_USERS NOT FOUND");
                                    throw new ResourceNotFoundException("Role", "role", ROLE_USERS);
                                });
                        roles.add(userRole);
                        log.info("Info : "+ user.getUsername() + " has assigned to ROLE_USERS");
                        break;
                }
            });
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        log.info("Info : Password has been encrypted" );
        Users entity = new Users(user.getUsername(), user.getEmail(), user.getPassword(), true);
        entity.setRoles(roles);
        Users save = userRepository.save(entity);
        log.info("Info : Successfully saved user data with username : " + user.getUsername());
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
            log.info("Info : Successfully updated user data with username : " + newUser.getUsername());
        }
        return users;
    }

    @Override
    public Users getUserByUsername(String username) {
        Users users = userRepository.findUsersByUsername(username).orElseThrow(
                () -> {
                    log.error("ERROR : User with username " + username + "NOT FOUND");
                    throw  new ResourceNotFoundException("User", "username", username);
                });
        log.info("Info : Successfully get user data with username : " + username);
        return users;
    }

    @Override
    public ApiResponse deleteUsers(String username) {
        Users users = userRepository.findUsersByUsername(username).orElseThrow(() -> {
            log.error("ERROR : User with username " + username + "NOT FOUND");
            throw new ResourceNotFoundException("User", "id", username);
        });
        userRepository.delete(users);
        log.info("Info : Successfully delete user data with username : " + username);
        return new ApiResponse(true, "Successfully delete profile " + username);
    }
}
