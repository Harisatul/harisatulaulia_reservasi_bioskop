package org.binar.movie.service;

import org.binar.movie.entities.Movie;
import org.binar.movie.payload.ApiResponse;

public interface MovieService {

    ApiResponse getAllMovie();
    ApiResponse addMovie(Movie movie);
    ApiResponse updateMovie(Long id);
    ApiResponse deleteMovie(Long id);
    ApiResponse findMovieById(Long id);

}
