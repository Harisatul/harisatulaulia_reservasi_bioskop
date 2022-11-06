package org.binar.challenge_4.service.impl;

import org.assertj.core.api.AssertionsForClassTypes;
import org.binar.challenge_4.entities.Movie;
import org.binar.challenge_4.entities.Users;
import org.binar.challenge_4.repository.MovieRepository;
import org.binar.challenge_4.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class MovieServiceImplTest {

    @Mock private MovieRepository movieRepository;

    private MovieService movieService;


    @BeforeEach
    void setUp() {
        this.movieService = new MovieServiceImpl(movieRepository);
    }

    @Test
    void getAllMovie() {
        List<Movie> movies = new ArrayList<>();
        given(movieRepository.findAll()).willReturn(movies);
        movieService.getAllMovie();
        verify(movieRepository).findAll();
    }

    @Test
    void addMovie() {
        Movie movie = new Movie();
        movie.setMovieTittle("Spiderman");
        when(movieRepository.save(any(Movie.class))).thenReturn(movie);
        movieService.addMovie(movie);
        verify(movieRepository, times(1)).save(any(Movie.class));
    }

    @Test
    void updateMovie() {
        Movie movie = new Movie();
        movie.setMovieTittle("Spiderman");
        given(movieRepository.findById(1l)).willReturn(Optional.of(movie));
    }

    @Test
    void deleteMovie() {
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setMovieTittle("Spiderman");
        given(movieRepository.findById(1L)).willReturn(Optional.of(movie));
        movieRepository.delete(movie);
        verify(movieRepository).delete(movie);
    }
}