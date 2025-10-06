package com.villageportal.service;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 30-Sep-25
 */


import com.villageportal.entity.Pupil;
import com.villageportal.enums.ClassLevel;
import com.villageportal.enums.Gender;
import com.villageportal.enums.Village;
import com.villageportal.request.PupilRequestDTO;
import com.villageportal.response.PupilResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public interface PupilService {

    List<PupilResponseDTO> getAllPupils();
    PupilResponseDTO getPupil(String firstName, String lastName, LocalDate birthDate);
    Pupil addPupil(PupilRequestDTO requestDTO, MultipartFile image);
    Pupil updatePupil(Pupil pupil);
    Pupil findPupilImage(String firstName, String lastName, LocalDate birthDate);
    List<Pupil> getUnsponsoredPupils();
    List<Pupil> findByVillage(Village village);
    List<Pupil> findByAgeRange(int minAge, int maxAge);
    List<Pupil> findByIsSponsoredFalseAndVillage(Village village);
    List<Pupil> findByIsSponsoredFalseAndClassLevel(ClassLevel classLevel);
    List<Pupil> findByIsSponsoredFalseAndGender(Gender gender);
}
