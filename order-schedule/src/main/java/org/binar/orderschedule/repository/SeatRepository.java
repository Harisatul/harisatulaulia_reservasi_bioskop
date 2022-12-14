package org.binar.orderschedule.repository;

import org.binar.orderschedule.entities.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

    List<Seat> findAllByStudiosIdAndIsAvailable(Long studiosId, Boolean isAvailable);
}
