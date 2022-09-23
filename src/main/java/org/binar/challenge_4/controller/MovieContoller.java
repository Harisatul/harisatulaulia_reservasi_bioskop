package org.binar.challenge_4.controller;

import org.binar.challenge_4.entities.Movie;
import org.binar.challenge_4.payload.ApiResponse;
import org.binar.challenge_4.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cinema/api/v1/movies")
public class MovieContoller {

    private final MovieService movieService;

    public MovieContoller(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllMovie(){
        ApiResponse apiResponse = movieService.getAllMovie();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ApiResponse> saveMovie(@RequestBody Movie movie){
        ApiResponse apiResponse =  movieService.addMovie(movie);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<ApiResponse> updateMovie(@PathVariable(value = "id") Long id){
        ApiResponse apiResponse = movieService.updateMovie(id);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deleteMovie(@PathVariable(value = "id") Long id){
        ApiResponse apiResponse = movieService.deleteMovie(id);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
