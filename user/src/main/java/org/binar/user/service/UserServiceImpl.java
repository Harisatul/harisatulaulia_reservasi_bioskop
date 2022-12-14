package org.binar.user.service;

import lombok.extern.slf4j.Slf4j;
import org.binar.user.dto.UserDTO;
import org.binar.user.entities.Role;
import org.binar.user.entities.Users;
import org.binar.user.exception.BadRequestException;
import org.binar.user.exception.ResourceNotFoundException;
import org.binar.user.payload.ApiResponse;
import org.binar.user.repository.RoleRepository;
import org.binar.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleRepository roleRepository;

    private static final String ERROR = "ERROR : ";
    private static final String ROLE_USERS = "ROLE_USERS";
    private static final String ROLE_ADMIN = "ROLE_ADMIN";
    private static final String INFO = "INFO  : ";

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
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
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
                if (ROLE_ADMIN.equals(role)) {
                    Role adminRole = roleRepository.findRoleByName(ROLE_ADMIN).orElseThrow(
                            () -> {
                                log.error(ERROR + "ROLE_ADMIN NOT FOUND");
                                throw new ResourceNotFoundException("Role", "role", ROLE_ADMIN);
                            });
                    roles.add(adminRole);
                    log.info("Info : " + user.getUsername() + " has assigned to ROLE_ADMIN");
                } else {
                    Role userRole = roleRepository.findRoleByName(ROLE_USERS).orElseThrow(
                            () -> {
                                log.error(ERROR + "ROLE_USERS NOT FOUND");
                                throw new ResourceNotFoundException("Role", "role", ROLE_USERS);
                            });
                    roles.add(userRole);
                    log.info("Info : " + user.getUsername() + " has assigned to ROLE_USERS");
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
