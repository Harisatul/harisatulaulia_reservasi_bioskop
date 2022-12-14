package org.binar.orderschedule.Service;

import org.binar.orderschedule.dto.ScheduleDTO;
import org.binar.orderschedule.payload.ApiResponse;

public interface ScheduleService {

    ApiResponse addSchedule(ScheduleDTO schedule);
    ApiResponse getSchedulebyMovieId(Long id);
    ApiResponse getSchedulebyMovieIsPremiered(Boolean id);

}
