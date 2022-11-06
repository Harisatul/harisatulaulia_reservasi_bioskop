package org.binar.challenge_4.repository;

import org.binar.challenge_4.entities.Users;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    Users users;

    @BeforeEach
    void setUp() {
        users = new Users();
        users.setUsername("haris");
        users.setEmail("haris@gmail.com");
        userRepository.save(users);
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    // findUsername and true
    @Test
    void findUsersByUsernameShouldTrue() {
        boolean isUsernamePresent = userRepository.findUsersByUsername("haris").isPresent();
        assertThat(isUsernamePresent).isTrue();

    }
    // findUsername and False
    @Test
    void findUsersByUsernameShouldFalse() {
        boolean isUsernamePresent = userRepository.findUsersByUsername("aulia").isPresent();
        assertThat(isUsernamePresent).isFalse();

    }

    // Email and true
    @Test
    void findUsersByEmailShouldTrue() {
        boolean isEmailPresent = userRepository.findUsersByEmail("haris@gmail.com").isPresent();
        assertThat(isEmailPresent).isTrue();
    }
    // Email and false
    @Test
    void findUsersByEmailShouldFalse() {
        boolean isEmailPresent = userRepository.findUsersByEmail("aulia@gmail.com").isPresent();
        assertThat(isEmailPresent).isFalse();
    }

}