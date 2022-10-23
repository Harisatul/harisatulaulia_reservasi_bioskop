package org.binar.challenge_4.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.binar.challenge_4.entities.Schedule;
import org.binar.challenge_4.entities.Users;
import org.binar.challenge_4.exception.ExceptionResponse;
import org.binar.challenge_4.payload.ApiResponse;
import org.binar.challenge_4.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cinema/api/v1/schedule")
public class ScheduleController {

    private ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    @Operation(
            tags = {"Schedule"},
            operationId = "addSchedule",
            summary = "add schedule data",
            description = "to add schedule data.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "This is the request body for add Schedule request.",
                    content = @Content(schema = @Schema(implementation = Schedule.class))),
            responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    content = @Content(
                            schema = @Schema(implementation = Schedule.class, type = "String"),mediaType = MediaType.APPLICATION_JSON_VALUE),
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
    public ResponseEntity<ApiResponse> saveSchedule(@RequestBody Schedule schedule){
        ApiResponse apiResponse = scheduleService.addSchedule(schedule);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


    @GetMapping("{id}")
    @Operation(
            tags = {"Schedule"},
            operationId = "getScheduleByMovie",
            summary = "get all Schedule available based on movie",
            description = "to fetch all schedule available",
            parameters = {
                    @Parameter(name = "movieId", description = "this is movie id. required to fetch schedule availability based on movie",
                            example = "3",schema = @Schema(type = "Long"), in = ParameterIn.PATH)},
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
    public ResponseEntity<ApiResponse> getAllSchedulesbyMovie(@PathVariable (value = "movieId") Long id){
        ApiResponse schedulebyMovieId = scheduleService.getSchedulebyMovieId(id);
        return new ResponseEntity<>(schedulebyMovieId, HttpStatus.OK);
    }

    @GetMapping("/show/{isPremiered}")
    @Operation(
            tags = {"Schedule"},
            operationId = "getScheduleByIsPremiered",
            summary = "get all Schedule available based on premiered movie. ",
            description = "to fetch all schedule available",
            parameters = {
                    @Parameter(name = "isPremiered", description = "this is is Premiered condition. required to fetch schedule availability based on movie premiered. Should pass true value",
                            example = "True",schema = @Schema(type = "Boolean"), in = ParameterIn.PATH)},
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
    public ResponseEntity<ApiResponse> getAllSchedulesbyMovieIsPremiered(@PathVariable (value = "isPremiered") Boolean is){
        ApiResponse schedulebyMovieIsPremiered = scheduleService.getSchedulebyMovieIsPremiered(is);
        return new ResponseEntity<>(schedulebyMovieIsPremiered, HttpStatus.OK);
    }
}
