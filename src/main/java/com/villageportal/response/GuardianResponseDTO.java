package com.villageportal.response;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 03-Oct-25
 */



import com.villageportal.enums.Relationship;
import lombok.Data;


@Data
public class GuardianResponseDTO {

    private String firstName;
    private String middleName;
    private String lastName;
    private String phoneNumber;
    private Relationship relationship; // e.g., uncle, aunt, neighbor
    private String address;
}
