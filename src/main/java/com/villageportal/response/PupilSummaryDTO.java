package com.villageportal.response;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 03-Oct-25
 */

import lombok.Data;

@Data
public class PupilSummaryDTO {

    private Long id;
    private String fullName;

    public PupilSummaryDTO(String fullName) {
        this.fullName = fullName;
    }
}
