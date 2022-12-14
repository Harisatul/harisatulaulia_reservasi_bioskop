package org.binar.orderschedule.repository;

import org.binar.orderschedule.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    Schedule findScheduleById(Long sheduleId);
    List<Schedule> findScheduleByMoviesId(@Param("id") Long id);
    List<Schedule> findScheduleByMoviesIsPremiered(@Param("is") Boolean is);



}
