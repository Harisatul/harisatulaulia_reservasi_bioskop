package org.binar.orderschedule.Service;


import org.binar.orderschedule.payload.ApiResponse;

public interface SeatService {

    ApiResponse getAllseatAvailable(Long scheduleId, Boolean avail);

}
