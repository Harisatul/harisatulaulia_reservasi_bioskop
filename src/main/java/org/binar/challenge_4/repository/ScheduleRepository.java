package org.binar.challenge_4.repository;

import org.binar.challenge_4.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findScheduleByMoviesId(@Param("id") Long id);
    List<Schedule> findScheduleByMoviesIsPremiered(@Param("is") Boolean is);
}
