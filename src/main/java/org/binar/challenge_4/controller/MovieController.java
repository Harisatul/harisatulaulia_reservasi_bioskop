package org.binar.challenge_4.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.servers.Server;
import org.binar.challenge_4.entities.Movie;
import org.binar.challenge_4.entities.Users;
import org.binar.challenge_4.exception.ExceptionResponse;
import org.binar.challenge_4.payload.ApiResponse;
import org.binar.challenge_4.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cinema/api/v1/movies")
public class MovieController {

    private  MovieService movieService;
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    @Operation(
            tags = {"Movie"},
            operationId = "getAllMovie",
            summary = "get all movie data",
            description = "to fetch all movie data",
            responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    content = @Content(
                                schema = @Schema(implementation = ApiResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE),
                    description = "Success Response."),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400",
                            content = @Content(schema = @Schema(implementation = ExceptionResponse.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE),
                            description = "Bad Request"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "405",
                            content = @Content(schema = @Schema(implementation = ExceptionResponse.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE),
                            description = "Method Not Allowed")
            }

    )
    public ResponseEntity<ApiResponse> getAllMovie(){
        ApiResponse apiResponse = movieService.getAllMovie();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @PostMapping
    @Operation(
            tags = {"Movie"},
            operationId = "addMovie",
            summary = "add movie data",
            description = "to add movie data. id required as path of endpoint",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "This is the request body for add Movie request.",
                    content = @Content(schema = @Schema(implementation = Movie.class))),
            responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    content = @Content(
                            schema = @Schema(implementation = Movie.class, type = "String"),mediaType = MediaType.APPLICATION_JSON_VALUE),
                    description = "Success Response."),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400",
                            content = @Content(schema = @Schema(implementation = ExceptionResponse.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE),
                            description = "Bad Request"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "405",
                            content = @Content(schema = @Schema(implementation = ExceptionResponse.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE),
                            description = "Method Not Allowed")
            }

    )
    public ResponseEntity<ApiResponse> saveMovie(@RequestBody Movie movie){
        ApiResponse apiResponse =  movieService.addMovie(movie);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @PutMapping("{id}")
    @Operation(
            tags = {"Movie"},
            operationId = "updateMovie",
            summary = "update movie data",
            description = "to update movie data. id required as path of endpoint",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "This is the request body for update Movie request.",
                    content = @Content(schema = @Schema(implementation = Movie.class))),
            parameters = {
                    @Parameter(name = "id", description = "this is movie id. represent id of movie",
                            example = "3",schema = @Schema(type = "Long"))},
            responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    content = @Content(
                            schema = @Schema(implementation = Movie.class, type = "String"),mediaType = MediaType.APPLICATION_JSON_VALUE),
                    description = "Success Response."),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400",
                            content = @Content(schema = @Schema(implementation = ExceptionResponse.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE),
                            description = "Bad Request"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "405",
                            content = @Content(schema = @Schema(implementation = ExceptionResponse.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE),
                            description = "Method Not Allowed")
            }

    )
    public ResponseEntity<ApiResponse> updateMovie(@PathVariable(value = "id") Long id){
        ApiResponse apiResponse = movieService.updateMovie(id);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("{movieId}")
    @Operation(
            tags = {"Movie"},
            operationId = "deletelMovie",
            summary = "delete Movie by Id ",
            description = "to delete movie data",
            parameters = {
                    @Parameter(name = "id", description = "this is movie id. represent id of movie",
                            example = "3",schema = @Schema(type = "Long"))},
            responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    content = @Content(
                            schema = @Schema(implementation = ApiResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE),
                    description = "Success Response."),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400",
                            content = @Content(schema = @Schema(implementation = ExceptionResponse.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE),
                            description = "Bad Request"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "405",
                            content = @Content(schema = @Schema(implementation = ExceptionResponse.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE),
                            description = "Method Not Allowed")
            }

    )

    public ResponseEntity<ApiResponse> deleteMovie(@PathVariable(value = "movieId") Long id){
        ApiResponse apiResponse = movieService.deleteMovie(id);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
