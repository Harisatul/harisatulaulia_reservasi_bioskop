package org.binar.movie.repository;

import org.binar.movie.entities.Movie;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class MovieRepositoryTest {

    @Autowired
    MovieRepository movieRepository;


    @AfterEach
    void tearDown() {
        movieRepository.deleteAll();
    }

    @Test
    void findMovieByIdAndShouldTrue() {

        Movie movie = new Movie();
        movie.setId(1l);
        movie.setMovieTittle("Spiderman : Far From Home");
        movie.setDescription("This is movie description");
        movie.setIsPremiered(true);
        movieRepository.save(movie);

        boolean isPresent = movieRepository.findById(1l).isPresent();

        assertThat(isPresent).isTrue();

    }

    @Test
    void findMovieByIdAndShouldFalse() {
        Movie movie = new Movie();
        movie.setId(1l);
        movie.setMovieTittle("Thor: Love and Thunder");
        movie.setDescription("This is thor movie description");
        movie.setIsPremiered(true);
        movieRepository.save(movie);
        boolean isPresent = movieRepository.findById(3l).isPresent();
        assertThat(isPresent).isFalse();

    }
}