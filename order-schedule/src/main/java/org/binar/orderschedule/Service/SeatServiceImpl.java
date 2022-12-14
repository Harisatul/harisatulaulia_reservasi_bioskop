package org.binar.orderschedule.Service;

import lombok.extern.slf4j.Slf4j;
import org.binar.orderschedule.entities.Schedule;
import org.binar.orderschedule.entities.Seat;
import org.binar.orderschedule.payload.ApiResponse;
import org.binar.orderschedule.repository.ScheduleRepository;
import org.binar.orderschedule.repository.SeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SeatServiceImpl implements SeatService {


    private SeatRepository seatRepository;
    private ScheduleRepository scheduleRepository;

    public SeatServiceImpl(SeatRepository seatRepository, ScheduleRepository scheduleRepository) {
        this.seatRepository = seatRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ApiResponse getAllseatAvailable(Long scheduleId, Boolean avail) {
        Schedule scheduleById = scheduleRepository.findScheduleById(scheduleId);
        Long id = scheduleById.getStudio().getId();
        List<Seat> allByStudiosIdAndIsAvailable = seatRepository.findAllByStudiosIdAndIsAvailable(id, true);
        log.info("Info :  fetch seat available data is success");
        return new ApiResponse(Boolean.TRUE, "success", allByStudiosIdAndIsAvailable);
    }

}
