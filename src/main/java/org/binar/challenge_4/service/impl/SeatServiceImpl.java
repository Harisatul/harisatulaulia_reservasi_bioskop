package org.binar.challenge_4.service.impl;

import org.binar.challenge_4.entities.Schedule;
import org.binar.challenge_4.entities.Seat;
import org.binar.challenge_4.entities.Studio;
import org.binar.challenge_4.payload.ApiResponse;
import org.binar.challenge_4.repository.ScheduleRepository;
import org.binar.challenge_4.repository.SeatRepository;
import org.binar.challenge_4.service.SeatService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {

    private SeatRepository seatRepository;
    private ScheduleRepository scheduleRepository;

    public SeatServiceImpl(SeatRepository seatRepository, ScheduleRepository scheduleRepository) {
        this.seatRepository = seatRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ApiResponse getAllseatAvailable(Long scheduleId,Boolean avail) {
        Schedule scheduleById = scheduleRepository.findScheduleById(scheduleId);
        Long id = scheduleById.getStudio().getId();
        List<Seat> allByStudiosIdAndIsAvailable = seatRepository.findAllByStudiosIdAndIsAvailable(id, true);
        return new ApiResponse(Boolean.TRUE, "success", allByStudiosIdAndIsAvailable);
    }
}
