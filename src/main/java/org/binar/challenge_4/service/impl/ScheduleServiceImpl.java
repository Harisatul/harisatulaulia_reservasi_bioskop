package org.binar.challenge_4.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.binar.challenge_4.dto.ScheduleDTO;
import org.binar.challenge_4.entities.Movie;
import org.binar.challenge_4.entities.Schedule;
import org.binar.challenge_4.entities.Studio;
import org.binar.challenge_4.exception.ResourceNotFoundException;
import org.binar.challenge_4.payload.ApiResponse;
import org.binar.challenge_4.repository.MovieRepository;
import org.binar.challenge_4.repository.ScheduleRepository;
import org.binar.challenge_4.repository.StudioRepository;
import org.binar.challenge_4.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ScheduleServiceImpl implements ScheduleService {

    private ScheduleRepository scheduleRepository;
    private MovieRepository movieRepository;
    private StudioRepository studioRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, MovieRepository movieRepository, StudioRepository studioRepository) {
        this.scheduleRepository = scheduleRepository;
        this.movieRepository = movieRepository;
        this.studioRepository = studioRepository;
    }

    @Override
    public ApiResponse addSchedule(ScheduleDTO scheduleDTO) {
        Movie movie = movieRepository.findById(scheduleDTO.getMovieId()).orElseThrow(() -> {
            log.error("movie with id {} couldn't found ", scheduleDTO.getMovieId());
            throw new ResourceNotFoundException("id", "iD", scheduleDTO.getMovieId());
        });
        Studio studio = studioRepository.findStudioByStudioCode(scheduleDTO.getStudioCode()).orElseThrow(() -> {
            log.error("movie with id {} couldn't found ", scheduleDTO.getMovieId());
            throw new ResourceNotFoundException("studioCode", "studioCode", scheduleDTO.getStudioCode());
        });
        Schedule schedule = new Schedule(scheduleDTO.getPremieredDate(), scheduleDTO.getPrice(),
                scheduleDTO.getHourStart(), scheduleDTO.getHourEnd(), movie, studio);
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
