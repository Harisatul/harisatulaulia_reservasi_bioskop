package org.binar.challenge_4.repository;

import org.binar.challenge_4.entities.Movie;
import org.binar.challenge_4.entities.Schedule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class ScheduleRepositoryTest {


    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    MovieRepository movieRepository;


    @BeforeEach
    void setUp() {
        Movie movie = new Movie();
        movie.setId(3l);
        movie.setMovieTittle("Spiderman");
        movieRepository.save(movie);
    }

    @AfterEach
    void tearDown() {
        scheduleRepository.deleteAll();
    }

    @Test
    void findScheduleById() {

        Schedule schedule = new Schedule();
        schedule.setId(1l);
        schedule.setPremieredDate(LocalDate.now());
        schedule.setPrice(35000);

        scheduleRepository.save(schedule);

        Schedule scheduleById = scheduleRepository.findScheduleById(1l);
        Long id = scheduleById.getId();
        System.out.println(id);
        assertThat(scheduleById.getId()).isEqualTo(1l);
    }

    @Test
//    @Disabled
    void findScheduleByMoviesId() {
        Schedule schedule = new Schedule();
        schedule.setId(1l);
        schedule.setPremieredDate(LocalDate.now());
        schedule.setPrice(35000);
        Movie movie = new Movie();
        movie.setId(3l);
        movie.setMovieTittle("Spiderman");
        schedule.setMovies(movie);
        movieRepository.save(movie);
        boolean present1 = movieRepository.findById(3l).isPresent();
        System.out.println(present1);
        scheduleRepository.save(schedule);
        List<Schedule> all = scheduleRepository.findAll();
        List<Schedule> scheduleByMoviesId = scheduleRepository.findScheduleByMoviesId(3l);
//        Long id = scheduleByMoviesId.get(0).getMovies().getId();
//        System.out.println(id);
        boolean present = scheduleByMoviesId.stream().findFirst().isPresent();
        assertThat(present).isTrue();

    }

    @Test
    @Disabled
    void findScheduleByMoviesIsPremiered() {
    }
}