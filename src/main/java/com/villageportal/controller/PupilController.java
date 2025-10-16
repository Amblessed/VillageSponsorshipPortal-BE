package com.villageportal.controller;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 30-Sep-25
 */

import com.villageportal.entity.Pupil;
import com.villageportal.enums.Village;
import com.villageportal.mapper.PupilMapper;
import com.villageportal.request.PupilRequestDTO;
import com.villageportal.request.TermReportRequestDTO;
import com.villageportal.response.PupilResponseDTO;
import com.villageportal.response.PupilSummaryDTO;
import com.villageportal.service.PupilService;
import com.villageportal.service.TermReportService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/api/pupils")
@RequiredArgsConstructor
public class PupilController {

    private final PupilService pupilService;
    private final PupilMapper pupilMapper;
    private final TermReportService termReportService;

    @GetMapping("/unsponsored/by-village")
    public ResponseEntity<List<Pupil>> getUnsponsoredByVillage(@RequestParam("name") Village village) {
        List<Pupil> pupils = pupilService.findByIsSponsoredFalseAndVillage(village);
        return ResponseEntity.ok(pupils);
    }

    @GetMapping
    public ResponseEntity<List<PupilResponseDTO>> getAllPupils() {
        List<PupilResponseDTO> pupils = pupilService.getAllPupils();
        return ResponseEntity.ok(pupils);
    }

    @GetMapping("/pupil")
    public ResponseEntity<PupilResponseDTO> getPupil(@RequestParam("firstName") String firstName,
                                                           @RequestParam("lastName") String lastName,
                                                           @RequestParam("birthDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthDate
    ) {
        PupilResponseDTO pupil = pupilService.getPupil(firstName, lastName, birthDate);
        return ResponseEntity.ok(pupil);
    }

    @GetMapping("/admin")
    public List<PupilSummaryDTO> getPupils() {
        List<PupilResponseDTO> pupils = pupilService.getAllPupils();
        return pupils.stream()
                .map(pupil -> new PupilSummaryDTO(pupil.getFirstName() + " " + pupil.getLastName()))
                .toList();
    }


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PupilResponseDTO> createPupil( @RequestPart("data") @Valid PupilRequestDTO request,
                                                        @RequestPart(value = "profileImage", required = false) MultipartFile image) {
        Pupil saved = pupilService.addPupil(request, image);
        return ResponseEntity.status(HttpStatus.CREATED).body(pupilMapper.toResponseDTO(saved));
    }

    @GetMapping("/photo")
    public ResponseEntity<byte[]> getPupilPhoto(@RequestParam("firstName") String firstName,
                                                @RequestParam("lastName") String lastName,
                                                @RequestParam("birthDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthDate) {
        Pupil pupil = pupilService.findPupilImage(firstName, lastName, birthDate);

        byte[] imageBytes = pupil.getProfileImage();
        if (imageBytes == null || imageBytes.length == 0) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No image available");
        }

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) // or IMAGE_PNG if needed
                .body(imageBytes);
    }


    @PutMapping("/pupil/term-payment")
    public ResponseEntity<String> updateTermPayment(@RequestBody @Valid Pupil updatedPupil) {
       System.out.println(updatedPupil);
       pupilService.updatePupil(updatedPupil);

        return ResponseEntity.ok()
                .body("✅ Pupil updated successfully");
    }


    @PostMapping("/term-reports")
    public ResponseEntity<?> addTermReport(@RequestBody @Valid TermReportRequestDTO dto) {
        try {
            termReportService.saveReport(dto);
            return ResponseEntity.ok("✅ Term report saved for pupil ID " + dto.getPupilId());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("❌ Pupil not found.");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("⚠️ Report for this term already exists.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("🚨 Something went wrong.");
        }
    }
}

