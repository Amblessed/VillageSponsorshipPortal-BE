package com.villageportal.response;


/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 30-Sep-25
 */


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PupilResponseDTO {

    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate birthDate;
    private String classLevel;
    private String village;
    private String story;
    private boolean sponsored;
    private String registrationDate;
    private String profileImageUrl; // If stored as URL or exposed via endpoint
    private ParentSummaryDTO parent;
    private GuardianResponseDTO guardian;
    private List<TermPaymentResponseDTO> payments;
}