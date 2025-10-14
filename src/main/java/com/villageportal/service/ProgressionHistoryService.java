package com.villageportal.service;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 12-Oct-25
 */

import com.villageportal.entity.ClassProgression;
import com.villageportal.entity.Grade;
import com.villageportal.entity.Pupil;
import com.villageportal.exception.ResourceNotFoundException;
import com.villageportal.repository.ClassProgressionRepository;
import com.villageportal.repository.PupilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgressionHistoryService {

    private final ClassProgressionRepository progressionRepository;
    private  final PupilRepository pupilRepository;


    public List<ClassProgression> getProgressionHistory(String firstName, String lastName, LocalDate birthDate) {
        return progressionRepository.findPupilProgression(firstName, lastName, birthDate);
    }

    @Transactional
    public ClassProgression saveProgression(String firstName, String lastName, LocalDate birthDate, ClassProgression progression) {
        Pupil pupil = pupilRepository
                .findByFirstNameAndLastNameAndBirthDate(firstName, lastName, birthDate)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("No pupil found for %s %s born on %s", firstName, lastName, birthDate)
                ));

        System.out.println("++++++++++++++");
        System.out.println(pupil);
        System.out.println(progression);
        System.out.println("++++++++++++++");
        progression.setPupil(pupil);
        return progressionRepository.save(progression);
    }
}
