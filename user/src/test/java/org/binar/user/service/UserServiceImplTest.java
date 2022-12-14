package org.binar.user.service;

import org.binar.user.dto.UserDTO;
import org.binar.user.entities.Role;
import org.binar.user.entities.Users;
import org.binar.user.exception.ResourceNotFoundException;
import org.binar.user.repository.RoleRepository;
import org.binar.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Mock
    private RoleRepository roleRepository;

    private UserService userService;


    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userRepository, roleRepository);
    }


    @Test
    @Disabled
    void loadUserByUsername() {
    }

    @Test
    void getAllUsers() {
        userService.getAllUsers();
        verify(userRepository).findAll();
    }

    @Test
    @Disabled
    void addUser() {
        Role role_admin = new Role();
        role_admin.setName("ROLE_ADMIN");
        given(roleRepository.findRoleByName("ROLE_ADMIN")).willReturn(Optional.of(role_admin));
        List<String> roleOfUsers = List.of("ROLE_ADMIN");
        UserDTO userDTO = new UserDTO("haris", "haris123", "haris@gmail.com", roleOfUsers);
        Users users = new Users(userDTO.getUsername(), userDTO.getEmail(), userDTO.getPassword(), true);
        when(userRepository.save(any(Users.class))).thenReturn(users);
        Users body = userService.addUser(userDTO).getBody();
        assertThat(body).usingRecursiveComparison().isEqualTo(users);
        verify(userRepository, times(1)).save(any(Users.class));

    }

    @Test
    @Disabled
    void updateUser() {

    }

    @Test
    void getUserByUsername() {
        Users users = new Users();
        users.setUsername("haris");
        given(userRepository.findUsersByUsername("haris")).willReturn(Optional.of(users));
        Users haris = userService.getUserByUsername("haris");
        assertThat(haris).usingRecursiveComparison().isEqualTo(users);
        verify(userRepository).findUsersByUsername("haris");

    }

    @Test
    void willThroWhenGetUserByUsername() {
        Users users = new Users();
        users.setUsername("haris");
        given(userRepository.findUsersByUsername("haris"))
                .willReturn(Optional.empty());

        assertThatThrownBy(() -> userService.getUserByUsername("haris"))
                .isInstanceOf(ResourceNotFoundException.class);
        verify(userRepository).findUsersByUsername("haris");

    }

    @Test
    void deleteUsers() {
        Users users = new Users();
        users.setUsername("haris");
        given(userRepository.findUsersByUsername("haris")).willReturn(Optional.of(users));
        userService.deleteUsers("haris");
        verify(userRepository).delete(users);
    }
}

