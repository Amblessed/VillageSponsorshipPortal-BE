package com.villageportal.repository;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 11-Oct-25
 */


import com.villageportal.entity.ClassProgression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ClassProgressionRepository extends JpaRepository<ClassProgression, Long> {

    @Query("SELECT cp FROM ClassProgression cp WHERE cp.pupil.firstName = :firstName AND cp.pupil.lastName = :lastName AND cp.pupil.birthDate = :birthDate")
    List<ClassProgression> findPupilProgression(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("birthDate") LocalDate birthDate);
}
