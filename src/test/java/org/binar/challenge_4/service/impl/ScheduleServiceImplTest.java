package org.binar.challenge_4.service.impl;

import org.apache.commons.collections.list.SynchronizedList;
import org.binar.challenge_4.entities.Schedule;
import org.binar.challenge_4.repository.MovieRepository;
import org.binar.challenge_4.repository.ScheduleRepository;
import org.binar.challenge_4.repository.StudioRepository;
import org.binar.challenge_4.service.ScheduleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class ScheduleServiceImplTest {

    @Mock private ScheduleRepository scheduleRepository;
    @Mock private MovieRepository movieRepository;
    @Mock private StudioRepository studioRepository;

    private ScheduleService scheduleService;


    @BeforeEach
    void setUp() {
        scheduleService = new ScheduleServiceImpl(scheduleRepository, movieRepository, studioRepository);
    }

    @Test
    void getSchedulebyMovieId() {
        List<Schedule> scheduleByMoviesId = new ArrayList<>();
        given(scheduleRepository.findScheduleByMoviesId(1l)).willReturn(scheduleByMoviesId);
        scheduleService.getSchedulebyMovieId(1l) ;
        verify(scheduleRepository).findScheduleByMoviesId(1l);
    }

    @Test
    void getSchedulebyMovieIsPremiered() {
        boolean temp =true;
        List<Schedule> scheduleByMoviesId = new ArrayList<>();
        given(scheduleRepository.findScheduleByMoviesIsPremiered(temp)).willReturn(scheduleByMoviesId);
       scheduleService.getSchedulebyMovieIsPremiered(temp);
        verify(scheduleRepository).findScheduleByMoviesIsPremiered(temp);
    }
}