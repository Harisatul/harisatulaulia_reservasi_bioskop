package org.binar.challenge_4.service;

import org.binar.challenge_4.entities.Movie;
import org.binar.challenge_4.payload.ApiResponse;

public interface MovieService {

    ApiResponse getAllMovie();
    ApiResponse addMovie(Movie movie);
    ApiResponse updateMovie(Long id);
    ApiResponse deleteMovie(Long id);


}
