package com.villageportal.config;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 03-Oct-25
 */


import com.villageportal.request.PupilRequestDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ResponsiblePartyValidator implements ConstraintValidator<ValidResponsibleParty, PupilRequestDTO> {

    @Override
    public boolean isValid(PupilRequestDTO dto, ConstraintValidatorContext context) {
        boolean hasParent = dto.getParent() != null;
        boolean hasGuardian = dto.getGuardian() != null;

        boolean isValid = hasParent ^ hasGuardian; // true if only one is present

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Please choose either parent or guardian, not both.")
                    .addPropertyNode("responsiblePartyType")
                    .addConstraintViolation();
        }

        return isValid; // true if only one is present
    }
}
