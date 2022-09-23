package org.binar.challenge_4.service;

import org.binar.challenge_4.entities.Schedule;
import org.binar.challenge_4.payload.ApiResponse;

public interface ScheduleService {

    ApiResponse addSchedule(Schedule schedule);
    ApiResponse getSchedulebyMovieId(Long id);
    ApiResponse getSchedulebyMovieIsPremiered(Boolean id);

}
