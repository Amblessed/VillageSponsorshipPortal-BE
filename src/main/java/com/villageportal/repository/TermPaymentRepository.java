package com.villageportal.repository;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 03-Oct-25
 */


import com.villageportal.entity.TermPayment;
import com.villageportal.enums.Term;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface TermPaymentRepository extends JpaRepository<TermPayment, Long> {


    @Query("SELECT tp FROM TermPayment tp WHERE tp.pupil.firstName = :firstName AND tp.pupil.lastName = :lastName AND tp.pupil.birthDate = :birthDate AND tp.term = :term")
    Optional<TermPayment> findByPupilAndTermNumber(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("birthDate") LocalDate birthDate, @Param("term") Term term);
}
