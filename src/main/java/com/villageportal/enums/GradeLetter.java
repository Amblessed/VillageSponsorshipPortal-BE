package com.villageportal.enums;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 12-Oct-25
 */


public enum GradeLetter {

    A("A"),
    B("B"),
    C("C"),
    D("D"),
    E("E"),
    F("F"),
    NOT_APPLICABLE("N/A");

    private final String label;

    GradeLetter(String label) {
        this.label = label;
    }

    public String toLabel() {
        return label;
    }
}
