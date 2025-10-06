package com.villageportal.request;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 30-Sep-25
 */

import lombok.Data;

@Data
public class ParentLookupDTO {

    private String fatherName;
    private String motherName;
    private String fatherPhone;
    private String motherPhone;
    private int numberOfChildren;
}
