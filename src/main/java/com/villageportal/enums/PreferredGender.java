package com.villageportal.enums;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 30-Sep-25
 */

import lombok.Getter;

@Getter
public enum PreferredGender {

    MALE("Male"),
    FEMALE("Female"),
    ANY("Any");

    private final String genderName;

    PreferredGender(String genderName) {
        this.genderName = genderName;
    }
}
