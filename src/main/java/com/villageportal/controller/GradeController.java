package com.villageportal.controller;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 09-Oct-25
 */

import com.villageportal.entity.Grade;
import com.villageportal.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/grades")
public class GradeController {

    private final GradeService gradeService;

    @PostMapping("/pupil")
    public ResponseEntity<Grade> saveGrade(@RequestParam String firstName, @RequestParam String lastName, @RequestParam LocalDate birthDate, @RequestBody Grade grade) {
        Grade saved = gradeService.saveGrade(firstName, lastName, birthDate, grade);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/pupil")
    public ResponseEntity<List<Grade>> getGradesByPupil(@RequestParam String firstName, @RequestParam String lastName, @RequestParam LocalDate birthDate) {
        List<Grade> grades = gradeService.findPupilGrades(firstName, lastName, birthDate);
        return ResponseEntity.ok(grades);
    }

    @PutMapping("/pupil")
    public ResponseEntity<String> updatePupilGrades(@RequestParam String firstName, @RequestParam String lastName, @RequestParam LocalDate birthDate, @RequestBody Grade grade) {
        gradeService.saveGrade(firstName, lastName, birthDate, grade);
        return ResponseEntity.ok("Updated");
    }
}
