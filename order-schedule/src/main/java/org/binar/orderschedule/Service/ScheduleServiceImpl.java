package org.binar.orderschedule.Service;

import lombok.extern.slf4j.Slf4j;
import org.binar.orderschedule.dto.ScheduleDTO;
import org.binar.orderschedule.entities.Movie;
import org.binar.orderschedule.entities.Schedule;
import org.binar.orderschedule.entities.Studio;
import org.binar.orderschedule.exception.ResourceNotFoundException;
import org.binar.orderschedule.payload.ApiResponse;
import org.binar.orderschedule.repository.ScheduleRepository;
import org.binar.orderschedule.repository.StudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.LinkedHashMap;
import java.util.List;

@Service
@Slf4j
@Transactional
public class ScheduleServiceImpl implements ScheduleService {
    private ScheduleRepository scheduleRepository;
    private WebClient webClient;

    private StudioRepository studioRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, WebClient webClient, StudioRepository studioRepository) {
        this.scheduleRepository = scheduleRepository;
        this.webClient = webClient;
        this.studioRepository = studioRepository;
    }

    @Override
    public ApiResponse addSchedule(ScheduleDTO scheduleDTO) {
        Long movieId = scheduleDTO.getMovieId();
        ApiResponse apiResponse = webClient.get()
                .uri("http://localhost:8080/cinema/api/v1/movies/getmovie/" + movieId)
                .retrieve()
                .bodyToMono(ApiResponse.class)
                .block();
        LinkedHashMap<String, String> movieData = (LinkedHashMap<String, String>) apiResponse.getData();
        String movieTittle = movieData.get("movieTittle");
        String description = movieData.get("description");
        Movie movie = new Movie();
        movie.setId(movieId);
        movie.setMovieTittle(movieTittle);
        movie.setDescription(description);
        movie.setIsPremiered(true);

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
