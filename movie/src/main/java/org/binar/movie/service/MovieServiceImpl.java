package org.binar.movie.service;

import lombok.extern.slf4j.Slf4j;
import org.binar.movie.entities.Movie;
import org.binar.movie.payload.ApiResponse;
import org.binar.movie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MovieServiceImpl implements MovieService {


    private MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;

    }
    @Override
    public ApiResponse getAllMovie() {
        List<Movie> all = movieRepository.findAll();
        log.info("Info :  fetch all movie data success");
        return new ApiResponse(Boolean.TRUE, "success", all);
    }

    @Override
    public ApiResponse addMovie(Movie movie) {
        Movie save = movieRepository.save(movie);
        log.info("Info :  add movie data success");
        return new ApiResponse(Boolean.TRUE, "successfully added" + movie.getMovieTittle(), save);
    }

    @Override
    public ApiResponse updateMovie(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow();
        log.info("Info :  add update data success");
        return new ApiResponse(Boolean.TRUE, "successfully update", movie);
    }

    @Override
    public ApiResponse deleteMovie(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow();
        movieRepository.delete(movie);
        log.info("Info :  add delete data success");
        return new ApiResponse(Boolean.TRUE, "successfully deleted movie with id " + id);
    }

    @Override
    public ApiResponse findMovieById(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow();
        return new ApiResponse(Boolean.TRUE, "success", movie);
    }


}

