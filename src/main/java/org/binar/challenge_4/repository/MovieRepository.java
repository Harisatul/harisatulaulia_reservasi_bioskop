package org.binar.challenge_4.repository;

import org.binar.challenge_4.entities.Movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Override
    Optional<Movie> findById(Long aLong);


}
