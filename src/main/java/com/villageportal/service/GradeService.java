package com.villageportal.service;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 10-Oct-25
 */


import com.villageportal.entity.Grade;
import com.villageportal.entity.Pupil;
import com.villageportal.exception.DuplicateGradeException;
import com.villageportal.exception.ResourceNotFoundException;
import com.villageportal.repository.GradeRepository;
import com.villageportal.repository.PupilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GradeService {

    private final GradeRepository gradeRepository;
    private final PupilRepository pupilRepository;


    public void updateGrades(String firstName, String lastName, LocalDate birthDate, List<Grade> grades) {
        Optional<Pupil> pupilOpt = pupilRepository.findByFirstNameAndLastNameAndBirthDate(firstName, lastName, birthDate);

        if (pupilOpt.isEmpty()) {
            throw new IllegalArgumentException("Pupil not found with provided identifiers.");
        }

        Pupil pupil = pupilOpt.get();

        // Assign pupil to each grade and save
        for (Grade grade : grades) {
            grade.setPupil(pupil);
        }

        gradeRepository.saveAll(grades);
    }


    @Transactional
    public Grade saveGrade(String firstName, String lastName, LocalDate birthDate, Grade grade) {
        Pupil pupil = pupilRepository
                .findByFirstNameAndLastNameAndBirthDate(firstName, lastName, birthDate)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("No pupil found for %s %s born on %s", firstName, lastName, birthDate)
                ));

        boolean exists = gradeRepository.existsByPupilIdAndSubjectAndTermAndAssessmentTypeAndClassLevel(
                pupil.getId(),
                grade.getSubject(),
                grade.getTerm(),
                grade.getAssessmentType(),
                grade.getClassLevel()
        );

        if (exists) {
            throw new DuplicateGradeException("Grade already exists for this pupil with the same subject, term, assessment type, and class level.");
        }

        grade.setPupil(pupil);
        return gradeRepository.save(grade);
    }


    public List<Grade> findPupilGrades(String firstName, String lastName, LocalDate birthDate) {
        return gradeRepository.findByPupilGrades( firstName, lastName, birthDate);
    }
}
