package org.binar.orderschedule.repository;

import org.binar.orderschedule.entities.Studio;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StudioRepositoryTest {

    @Autowired
    StudioRepository studioRepository;

    @BeforeEach
    void setUp() {
        Studio studioA = new Studio();
        studioA.setStudioCode("A");
        Studio studioB = new Studio();
        studioB.setStudioCode("B");
        Studio studioC = new Studio();
        studioC.setStudioCode("C");

        studioRepository.saveAll(List.of(studioA, studioB, studioC));

    }

    @AfterEach
    void tearDown() {
        studioRepository.deleteAll();
    }

    @Test
    void findStudioByStudioCodeShouldTrue() {
        boolean a = studioRepository.findStudioByStudioCode("A").isPresent();
        boolean b = studioRepository.findStudioByStudioCode("B").isPresent();
        boolean c = studioRepository.findStudioByStudioCode("C").isPresent();

        assertThat(a).isTrue();
        assertThat(b).isTrue();
        assertThat(c).isTrue();
    }

    @Test
    void findStudioByStudioCodeShouldFalse() {
        boolean a = studioRepository.findStudioByStudioCode("D").isPresent();
        boolean b = studioRepository.findStudioByStudioCode("E").isPresent();
        boolean c = studioRepository.findStudioByStudioCode("F").isPresent();

        assertThat(a).isFalse();
        assertThat(b).isFalse();
        assertThat(c).isFalse();
    }
}