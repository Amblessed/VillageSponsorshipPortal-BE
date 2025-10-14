package com.villageportal.service;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 30-Sep-25
 */


import com.villageportal.entity.*;
import com.villageportal.enums.ClassLevel;
import com.villageportal.enums.Gender;
import com.villageportal.enums.Village;
import com.villageportal.exception.ResourceNotFoundException;
import com.villageportal.mapper.GuardianMapper;
import com.villageportal.mapper.ParentMapper;
import com.villageportal.mapper.PupilMapper;
import com.villageportal.repository.GuardianRepository;
import com.villageportal.repository.ParentRepository;
import com.villageportal.repository.PupilRepository;
import com.villageportal.request.GuardianRequestDTO;
import com.villageportal.request.ParentRequestDTO;
import com.villageportal.request.PupilRequestDTO;
import com.villageportal.response.PupilResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PupilServiceImpl implements PupilService{

    private final PupilRepository pupilRepository;
    private final ParentRepository parentRepository;
    private final GuardianRepository guardianRepository;
    private final PupilMapper pupilMapper;
    private final ParentMapper parentMapper;
    private final GuardianMapper guardianMapper;


    @Override
    @Transactional
    public List<PupilResponseDTO> getAllPupils() {
        List<Pupil> pupils = pupilRepository.findAll();
        return pupils.stream().map(pupilMapper::toResponseDTO).toList();
    }

    @Override
    public PupilResponseDTO getPupil(String firstName, String lastName, LocalDate birthDate) {
        Pupil pupil = pupilRepository.findByFirstNameAndLastNameAndBirthDate(firstName, lastName, birthDate)
                .orElseThrow(() -> new EntityNotFoundException("Pupil not found"));
        return pupilMapper.toResponseDTO(pupil);
    }

    @Override
    public Pupil addPupil(PupilRequestDTO requestDTO, MultipartFile image) {
        byte[] imageBytes = null;
        Pupil pupil;

        if (image != null && !image.isEmpty()) {
            try {
                imageBytes = image.getBytes();
            } catch (IOException e) {
                throw new RuntimeException("Failed to read image bytes", e);
            }
        }
        ParentRequestDTO parentRequestDTO = requestDTO.getParent();
        if (parentRequestDTO != null) {
            PersonDetails father = parentRequestDTO.getFather();
            PersonDetails mother = parentRequestDTO.getMother();
            Optional<Parent> parent = parentRepository.findByParent(father.getLastName(), father.getPhone(), mother.getLastName(), mother.getPhone(), parentRequestDTO.getNumberOfChildren());
            if (parent.isEmpty()) {
                Parent newParent = parentMapper.toEntity(parentRequestDTO);
                parentRepository.save(newParent);
                pupil = pupilMapper.toEntity(requestDTO, newParent, imageBytes);
            }
            else {
                pupil = pupilMapper.toEntity(requestDTO, parent.get(), imageBytes);
            }
            return pupilRepository.save(pupil);
        }
        else {
            GuardianRequestDTO guardianRequestDTO = requestDTO.getGuardian();
            Optional<Guardian> guardian = guardianRepository.findByGuardian(guardianRequestDTO.getFirstName(), guardianRequestDTO.getLastName(), guardianRequestDTO.getPhoneNumber());
            if (guardian.isEmpty()) {
                Guardian newGuardian = guardianMapper.toEntity(guardianRequestDTO);
                guardianRepository.save(newGuardian);
                pupil = pupilMapper.toEntity(requestDTO, newGuardian, imageBytes);
            }
            else{
                pupil = pupilMapper.toEntity(requestDTO, guardian.get(), imageBytes);
            }
            return pupilRepository.save(pupil);
        }
    }

    @Override
    public Pupil updatePupil(Pupil pupil) {
        String firstName = pupil.getFirstName();
        String lastName = pupil.getLastName();
        LocalDate birthDate = pupil.getBirthDate();
        Pupil existingPupil = pupilRepository.findByFirstNameAndLastNameAndBirthDate(firstName, lastName, birthDate)
                .orElseThrow(() -> new ResourceNotFoundException("Pupil not found"));
        if (pupil.getPayments().isEmpty()) {
            throw new IllegalArgumentException("No term payment provided");
        }


        Set<String> existingKeys = existingPupil.getPayments().stream()
                .map(tp -> tp.getTerm() + "#" + tp.getClassLevel())
                .collect(Collectors.toSet());

        for (TermPayment termPayment : pupil.getPayments()) {
            String key = termPayment.getTerm() + "#" + termPayment.getClassLevel();
            if (existingKeys.add(key)) { // add() returns false if key already exists
                existingPupil.addTermPayment(termPayment);
            }
        }
        return pupilRepository.save(existingPupil);
    }

    @Override
    public Pupil findPupilImage(String firstName, String lastName, LocalDate birthDate) {
        return pupilRepository.findByFirstNameAndLastNameAndBirthDate(firstName, lastName, birthDate)
                .orElseThrow(() -> new ResourceNotFoundException("Pupil not found"));
    }

    @Override
    public List<Pupil> getUnsponsoredPupils() {
        Pageable pageable = PageRequest.of(0, 10);
        return pupilRepository.findBySponsoredFalse(pageable);
    }

    @Override
    public List<Pupil> findByVillage(Village village) {
        return pupilRepository.findByVillage(village);
    }

    @Override
    public List<Pupil> findByAgeRange(int minAge, int maxAge) {
        return pupilRepository.findByAgeRange(minAge, maxAge);
    }

    @Override
    public List<Pupil> findByIsSponsoredFalseAndVillage(Village village) {
        return pupilRepository.findByIsSponsoredFalseAndVillage(village);
    }

    @Override
    public List<Pupil> findByIsSponsoredFalseAndClassLevel(ClassLevel classLevel) {
        return pupilRepository.findByIsSponsoredFalseAndClassLevel(classLevel);
    }

    @Override
    public List<Pupil> findByIsSponsoredFalseAndGender(Gender gender) {
        return pupilRepository.findByIsSponsoredFalseAndGender(gender);
    }

    public void markAsSponsored(Long pupilId) {
        Pupil pupil = pupilRepository.findById(pupilId)
                .orElseThrow(() -> new EntityNotFoundException("Pupil not found"));
        pupil.setSponsored(true);
        pupilRepository.save(pupil);
    }

    @Transactional
    public void promotePupil(Pupil pupil, ClassLevel nextLevel, LocalDate startDate) {
        pupil.promoteToNextClass(nextLevel, startDate);
        pupilRepository.save(pupil);
    }

    @Transactional
    public void promoteEligiblePupils(LocalDate promotionDate) {
        List<Pupil> allPupils = pupilRepository.findAll();

        for (Pupil pupil : allPupils) {
            ClassLevel current = pupil.getClassLevel();
            ClassLevel next = getNextClassLevel(current);

            if (next != null) {
                pupil.promoteToNextClass(next, promotionDate);
                pupilRepository.save(pupil);
            }
        }
    }

    private ClassLevel getNextClassLevel(ClassLevel current) {
        if (current == null) return null;

        ClassLevel[] levels = ClassLevel.values();
        int index = Arrays.asList(levels).indexOf(current);

        return (index >= 0 && index < levels.length - 1) ? levels[index + 1] : null;
    }






}
