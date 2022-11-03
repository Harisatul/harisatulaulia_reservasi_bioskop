package org.binar.challenge_4.repository;

import org.binar.challenge_4.entities.Seat;
import org.binar.challenge_4.entities.Studio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    List<Seat> findAllByStudiosIdAndIsAvailable(Long studiosId,  Boolean isAvailable);
}
