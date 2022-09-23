package org.binar.challenge_4.service;

import org.binar.challenge_4.payload.ApiResponse;

public interface ScheduleService {

    ApiResponse getAllSchedule();
    ApiResponse getScheduleFromMovie();

}
