package com.villageportal.response;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 03-Oct-25
 */



import lombok.Data;


@Data
public class GuardianResponseDTO {

    private String firstName;
    private String middleName;
    private String lastName;
    private String phoneNumber;
    private String relationshipToPupil; // e.g., uncle, aunt, neighbor
    private String address;
}
