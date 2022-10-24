package org.binar.challenge_4.service;

import org.binar.challenge_4.dto.ScheduleDTO;
import org.binar.challenge_4.payload.ApiResponse;

public interface ScheduleService {

    ApiResponse addSchedule(ScheduleDTO schedule);
    ApiResponse getSchedulebyMovieId(Long id);
    ApiResponse getSchedulebyMovieIsPremiered(Boolean id);

}
