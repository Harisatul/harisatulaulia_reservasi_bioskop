package org.binar.challenge_4.service.impl;

import org.assertj.core.api.AssertionsForClassTypes;
import org.binar.challenge_4.entities.Schedule;
import org.binar.challenge_4.entities.Seat;
import org.binar.challenge_4.entities.Studio;
import org.binar.challenge_4.repository.ScheduleRepository;
import org.binar.challenge_4.repository.SeatRepository;
import org.binar.challenge_4.repository.StudioRepository;
import org.binar.challenge_4.service.SeatService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class SeatServiceImplTest {

    @Mock private SeatRepository seatRepository;
    @Mock private ScheduleRepository scheduleRepository;
    @Mock  private StudioRepository studioRepository;

    private SeatService seatService;

    @BeforeEach
    void setUp() {
        seatService = new SeatServiceImpl(seatRepository, scheduleRepository);
    }

    @Test
    void getAllseatAvailable() {
        Studio studio = new Studio();
        studio.setId(1L);
        given(studioRepository.save(any(Studio.class))).willReturn(studio);
        studioRepository.save(studio);

        Schedule schedule = new Schedule();
        schedule.setId(2L);
        schedule.setStudio(studio);
        scheduleRepository.save(schedule);

        long id = 2l;


        given(scheduleRepository.findScheduleById(id)).willReturn(schedule);
        List<Seat> seats = new ArrayList<>();
        given(seatRepository.findAllByStudiosIdAndIsAvailable(1l, true)).willReturn(seats);

        seatService.getAllseatAvailable(2l, true);

        verify(scheduleRepository, times(1)).findScheduleById(2l);
        verify(seatRepository, times(1)).findAllByStudiosIdAndIsAvailable(1l, true);



    }
}