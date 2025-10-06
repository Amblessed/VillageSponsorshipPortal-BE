package com.villageportal.enums;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 26-Sep-25
 */

import lombok.Getter;

@Getter
public enum Gender {
    MALE("Male"),
    FEMALE("Female"),
    ANY("Any");

    private final String genderName;

    Gender(String genderName) {
        this.genderName = genderName;
    }


}
