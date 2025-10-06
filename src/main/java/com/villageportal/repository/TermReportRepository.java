package com.villageportal.repository;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 03-Oct-25
 */


import com.villageportal.entity.Pupil;
import com.villageportal.entity.TermReport;
import com.villageportal.enums.Term;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TermReportRepository extends JpaRepository<TermReport, Long> {

    boolean existsByPupilAndTerm(Pupil pupil, Term term);




}
