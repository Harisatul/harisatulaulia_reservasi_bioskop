package org.binar.challenge_4.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.binar.challenge_4.entities.Movie;
import org.binar.challenge_4.entities.Schedule;
import org.binar.challenge_4.payload.ApiResponse;
import org.binar.challenge_4.repository.ScheduleRepository;
import org.binar.challenge_4.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class ScheduleServiceImpl implements ScheduleService {

    private ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ApiResponse addSchedule(Schedule schedule) {
        Schedule save = scheduleRepository.save(schedule);
        log.info("Info :  add update data success");
        return new ApiResponse(Boolean.TRUE, "success", save);
    }

    @Override
    public ApiResponse getSchedulebyMovieId(Long id) {
        List<Schedule> scheduleByMoviesId = scheduleRepository.findScheduleByMoviesId(id);
        log.info("Info :  fetch schedule data by movie success");
        return new ApiResponse(Boolean.TRUE, "SUCCESS", scheduleByMoviesId);
    }

    @Override
    public ApiResponse getSchedulebyMovieIsPremiered(Boolean id) {
        List<Schedule> scheduleByMoviesIsPremiered = scheduleRepository.findScheduleByMoviesIsPremiered(id);
        log.info("Info :  fetch schedule data by isPremiered success");
        return new ApiResponse(Boolean.TRUE, "SUCCESS", scheduleByMoviesIsPremiered);
    }
}
