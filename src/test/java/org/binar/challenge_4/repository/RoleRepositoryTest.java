package org.binar.challenge_4.repository;

import org.binar.challenge_4.entities.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class RoleRepositoryTest {

    @Autowired
    RoleRepository roleRepository;

    @AfterEach
    void tearDown() {
        roleRepository.deleteAll();
    }


    @Test
    void findRoleByNameAndShouldBeFound() {
        Role role = new Role();
        role.setName("ROLE_USERS");
        roleRepository.save(role);
        boolean isPresent = roleRepository.findRoleByName("ROLE_USERS").isPresent();
        assertThat(isPresent).isTrue();
    }

    @Test
    void findRoleByNameAndShouldNotFound() {
        Role role = new Role();
        role.setName("ROLE_USERS");
        roleRepository.save(role);
        boolean isPresent = roleRepository.findRoleByName("ROLE_ADMIN").isPresent();
        assertThat(isPresent).isFalse();
    }

}