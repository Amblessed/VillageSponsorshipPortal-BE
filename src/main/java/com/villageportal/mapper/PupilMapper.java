package com.villageportal.mapper;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 30-Sep-25
 */


import com.villageportal.response.TermPaymentResponseDTO;
import com.villageportal.entity.*;
import com.villageportal.request.PupilRequestDTO;
import com.villageportal.response.GuardianResponseDTO;
import com.villageportal.response.ParentSummaryDTO;
import com.villageportal.response.PupilResponseDTO;
import org.springframework.stereotype.Component;


@Component
public class PupilMapper {

    public Pupil toEntity(PupilRequestDTO dto, Parent parent, byte[] profileImage) {
        return buildPupil(dto, profileImage, parent, null);
    }

    public Pupil toEntity(PupilRequestDTO dto, Guardian guardian, byte[] profileImage) {
        return buildPupil(dto, profileImage, null, guardian);
    }

    private Pupil buildPupil(PupilRequestDTO dto, byte[] profileImage, Parent parent, Guardian guardian) {
        Pupil pupil = new Pupil();
        pupil.setClassLevel(dto.getClassLevel());
        pupil.setStory(dto.getStory());
        pupil.setFirstName(dto.getFirstName());
        pupil.setMiddleName(dto.getMiddleName());
        pupil.setLastName(dto.getLastName());
        pupil.setGender(dto.getGender());
        pupil.setVillage(dto.getVillage());
        pupil.setBirthDate(dto.getBirthDate());
        pupil.setSponsored(dto.isSponsored());
        pupil.setProfileImage(profileImage);
        pupil.setParent(parent);
        pupil.setGuardian(guardian);
        return pupil;
    }


    public PupilResponseDTO toResponseDTO(Pupil pupil) {
        PupilResponseDTO dto = new PupilResponseDTO();
        dto.setFirstName(pupil.getFirstName());
        dto.setMiddleName(pupil.getMiddleName());
        dto.setLastName(pupil.getLastName());
        dto.setBirthDate(pupil.getBirthDate());
        dto.setClassLevel(pupil.getClassLevel());
        dto.setVillage(pupil.getVillage());
        dto.setStory(pupil.getStory());
        dto.setSponsored(pupil.isSponsored());
        dto.setRegistrationDate(pupil.getFormattedRegistrationDate());

        dto.setPayments(pupil.getPayments().stream().map(this::toTermPaymentResponseDTO).toList());

        // Optional: expose image as URL or endpoint reference
        dto.setProfileImageUrl("/api/pupils/" + pupil.getId() + "/image");

        if (pupil.getParent() != null) {
            dto.setParent(toParentSummary(pupil.getParent()));
        }

        if (pupil.getGuardian() != null) {
            dto.setGuardian(toGuardianSummary(pupil.getGuardian()));
        }

        return dto;
    }

    public ParentSummaryDTO toParentSummary(Parent parent) {

        PersonDetails father = parent.getFather();
        PersonDetails mother = parent.getMother();

        ParentSummaryDTO dto = new ParentSummaryDTO();
        dto.setFatherFullName(father.getFullName());
        dto.setFatherOccupation(father.getOccupation());
        dto.setFatherPhone(father.getPhone());
        dto.setFatherAlive(father.getAlive());
        dto.setMotherFullName(mother.getFullName());
        dto.setMotherOccupation(mother.getOccupation());
        dto.setMotherPhone(mother.getPhone());
        dto.setMotherAlive(mother.getAlive());
        dto.setNumberOfChildren(parent.getNumberOfChildren());
        dto.setParentsDivorced(parent.getParentsDivorced());
        return dto;
    }

    public GuardianResponseDTO toGuardianSummary(Guardian guardian) {

        GuardianResponseDTO responseDTO = new GuardianResponseDTO();
        responseDTO.setPhoneNumber(guardian.getPhoneNumber());
        responseDTO.setRelationship(guardian.getRelationship());
        responseDTO.setAddress(guardian.getAddress());
        responseDTO.setFirstName(guardian.getFirstName());
        responseDTO.setMiddleName(guardian.getMiddleName());
        responseDTO.setLastName(guardian.getLastName());
        return responseDTO;
    }

    private TermPaymentResponseDTO toTermPaymentResponseDTO(TermPayment termPayment) {
        TermPaymentResponseDTO responseDTO = new TermPaymentResponseDTO();
        responseDTO.setId(termPayment.getId());
        responseDTO.setTerm(termPayment.getTerm());
        responseDTO.setClassLevel(termPayment.getClassLevel());
        responseDTO.setTotalPaid(termPayment.getTotalPaid());
        responseDTO.setPaymentDate(termPayment.getPaymentDate());
        return responseDTO;
    }
}
