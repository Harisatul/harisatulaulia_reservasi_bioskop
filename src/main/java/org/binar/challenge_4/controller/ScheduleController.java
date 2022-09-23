package org.binar.challenge_4.controller;

import org.binar.challenge_4.entities.Schedule;
import org.binar.challenge_4.payload.ApiResponse;
import org.binar.challenge_4.service.ScheduleService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<ApiResponse> saveSchedule(@RequestBody Schedule schedule){
        ApiResponse apiResponse = scheduleService.addSchedule(schedule);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


    @GetMapping("{id}")
    public ResponseEntity<ApiResponse> getAllSchedulesbyMovie(@PathVariable (value = "id") Long id){
        ApiResponse schedulebyMovieId = scheduleService.getSchedulebyMovieId(id);
        return new ResponseEntity<>(schedulebyMovieId, HttpStatus.OK);
    }

    @GetMapping("/show/{is}")
    public ResponseEntity<ApiResponse> getAllSchedulesbyMovieIsPremiered(@PathVariable (value = "is") Boolean is){
        ApiResponse schedulebyMovieIsPremiered = scheduleService.getSchedulebyMovieIsPremiered(is);
        return new ResponseEntity<>(schedulebyMovieIsPremiered, HttpStatus.OK);
    }
}
