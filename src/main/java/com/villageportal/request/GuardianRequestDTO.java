package com.villageportal.request;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 03-Oct-25
 */

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuardianRequestDTO {

    private String firstName;
    private String middleName;
    private String lastName;
    private String occupation;
    private String phoneNumber;
    private String address;
    private String relationship;

}
