package com.villageportal.service;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 03-Oct-25
 */

import com.villageportal.entity.Pupil;
import com.villageportal.entity.SchoolClass;
import com.villageportal.entity.TermReport;
import com.villageportal.repository.PupilRepository;
import com.villageportal.repository.TermReportRepository;
import com.villageportal.request.TermReportRequestDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TermReportService {

    private final PupilRepository pupilRepository;
    private final TermReportRepository termReportRepository;


    public void saveReport(TermReportRequestDTO dto) {
        // 1. Fetch pupil
        Pupil pupil = pupilRepository.findById(dto.getPupilId())
                .orElseThrow(() -> new EntityNotFoundException("Pupil not found"));

        // 2. Get SchoolClass from pupil
        SchoolClass schoolClass = pupil.getSchoolClass();

        // 3. Check if report already exists for this pupil + term
        boolean exists = termReportRepository.existsByPupilAndTerm(pupil, dto.getTerm());
        if (exists) {
            throw new IllegalStateException("Report for this term already exists for this pupil.");
        }

        // 4. Create and save TermReport
        TermReport report = new TermReport();
        report.setPupil(pupil);
        report.setSchoolClass(schoolClass);
        report.setTerm(dto.getTerm());
        report.setSummary(dto.getSummary());
        report.setDate(dto.getDate());
        report.setTeacherName(dto.getTeacherName());

        termReportRepository.save(report);
    }
}
