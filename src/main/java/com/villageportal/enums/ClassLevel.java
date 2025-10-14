package com.villageportal.enums;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 30-Sep-25
 */

import lombok.Getter;

@Getter
public enum ClassLevel {

    PRE_NURSERY("Pre Nursery"),
    NURSERY_ONE("Nursery 1"),
    NURSERY_TWO("Nursery 2"),
    NURSERY_THREE("Nursery 3"),
    PRIMARY_ONE("Primary 1"),
    PRIMARY_TWO("Primary 2"),
    PRIMARY_THREE("Primary 3"),
    PRIMARY_FOUR("Primary 4"),
    PRIMARY_FIVE("Primary 5"),
    PRIMARY_SIX("Primary 6");

    private final String label;

    ClassLevel(String label) {
        this.label = label;
    }

    public String toLabel() {
        return label;
    }
}
