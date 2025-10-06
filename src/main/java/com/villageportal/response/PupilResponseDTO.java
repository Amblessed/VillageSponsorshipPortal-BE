package com.villageportal.response;


/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 30-Sep-25
 */


import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PupilResponseDTO {

    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate birthDate;
    private String classLevelLabel;
    private String village;
    private String story;
    private boolean sponsored;
    private String formattedRegistrationDate;
    private String profileImageUrl; // If stored as URL or exposed via endpoint
    private ParentSummaryDTO parent;
    private GuardianResponseDTO guardian;
    private List<TermPaymentResponseDTO> payments;


}