package com.villageportal.controller;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 11-Oct-25
 */

import com.villageportal.entity.ClassProgression;
import com.villageportal.entity.Pupil;
import com.villageportal.exception.ResourceNotFoundException;
import com.villageportal.repository.ClassProgressionRepository;
import com.villageportal.repository.PupilRepository;
import com.villageportal.service.PupilService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = {
        "http://localhost:3000",
        "https://village-sponsorship-portal-fe.vercel.app"
})
@RestController
@RequestMapping("/api/pupils/progression-history/pupil")
@RequiredArgsConstructor
public class ClassProgressionController {

    private final PupilService pupilService;
    private final PupilRepository pupilRepository;
    private final ClassProgressionRepository progressionRepository;

    @GetMapping("")
    public ResponseEntity<List<ClassProgression>> getProgressionHistory(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthDate
    ) {
        Pupil pupilOpt = pupilRepository.findByFirstNameAndLastNameAndBirthDate(firstName, lastName, birthDate)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("No pupil found for %s %s born on %s", firstName, lastName, birthDate))
        );

        List<ClassProgression> history = progressionRepository.findPupilProgression(pupilOpt.getFirstName(), pupilOpt.getLastName(), pupilOpt.getBirthDate());
        return ResponseEntity.ok(history);
    }
}
