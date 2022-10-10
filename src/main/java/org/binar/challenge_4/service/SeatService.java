package org.binar.challenge_4.service;

import org.binar.challenge_4.payload.ApiResponse;

public interface SeatService {

    ApiResponse getAllseatAvailable(Long scheduleId, Boolean avail);

}
