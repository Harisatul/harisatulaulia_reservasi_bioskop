package org.binar.challenge_4.repository;

import org.binar.challenge_4.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

   Optional<Users> findUsersByUsername(String name);
   Optional<Users> findUsersByEmail(String email);
}
