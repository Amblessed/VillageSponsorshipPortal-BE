package com.villageportal.repository;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 09-Oct-25
 */


import com.villageportal.entity.Grade;
import com.villageportal.enums.Assessment;
import com.villageportal.enums.ClassLevel;
import com.villageportal.enums.Term;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {


    @Query("SELECT g FROM Grade g WHERE g.pupil.firstName = :firstName AND g.pupil.lastName = :lastName AND g.pupil.birthDate = :birthDate")
    List<Grade> findByPupilGrades(String firstName, String lastName, LocalDate birthDate);

    boolean existsByPupilIdAndSubjectAndTermAndAssessmentTypeAndClassLevel(
            Long pupilId,
            String subject,
            Term term,
            Assessment assessmentType,
            ClassLevel classLevel
    );
}
