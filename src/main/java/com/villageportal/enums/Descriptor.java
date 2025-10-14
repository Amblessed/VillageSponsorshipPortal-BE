package com.villageportal.enums;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 12-Oct-25
 */


public enum Descriptor {
    EXCELLENT("Excellent"),
    VERY_GOOD("Very Good"),
    GOOD("Good"),
    FAIR("Fair"),
    POOR("Poor"),
    FAIL("Fail"),
    NOT_APPLICABLE("N/A");

    private final String label;

    Descriptor(String label) {
        this.label = label;
    }

    public String toLabel() {
        return label;
    }
}
