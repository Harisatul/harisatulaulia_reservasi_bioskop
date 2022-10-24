package org.binar.challenge_4.repository;

import org.binar.challenge_4.entities.Studio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudioRepository extends JpaRepository<Studio, Long> {

    Optional<Studio> findStudioByStudioCode(String studioCode);


}
