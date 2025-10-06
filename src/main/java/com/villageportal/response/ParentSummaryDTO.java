package com.villageportal.response;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 30-Sep-25
 */

import lombok.Data;

@Data
public class ParentSummaryDTO {

    private String fatherFullName;
    private String motherFullName;
    private String fatherOccupation;
    private String motherOccupation;
    private String fatherPhone;
    private Boolean fatherAlive;
    private String motherPhone;
    private Boolean motherAlive;
    private int numberOfChildren;
    private Boolean parentsDivorced;
}
