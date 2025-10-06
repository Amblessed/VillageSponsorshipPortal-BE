package com.villageportal.request;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 30-Sep-25
 */

import com.villageportal.config.ValidResponsibleParty;
import com.villageportal.enums.ClassLevel;
import com.villageportal.enums.Gender;
import com.villageportal.enums.ResponsiblePartyType;
import com.villageportal.enums.Village;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@ValidResponsibleParty
@Data
public class PupilRequestDTO {

    @NotBlank
    private String firstName;

    private String middleName;

    @NotBlank
    private String lastName;

    @NotNull
    @Past
    private LocalDate birthDate;

    @NotNull
    private ClassLevel classLevel;

    @NotNull
    private Gender gender;

    @NotNull
    private Village village;

    @Size(max = 1500)
    private String story;

    private boolean sponsored = false;

    @NotNull
    private ResponsiblePartyType responsiblePartyType; // PARENT or GUARDIAN

    private ParentRequestDTO parent;

    private GuardianRequestDTO guardian;



}
