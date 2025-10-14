package com.villageportal.enums;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 12-Oct-25
 */


public enum Assessment {

    EXAM("Exam"),
    HOMEWORK("Homework"),
    CLASSWORK("Classwork");


    private final String assessmentName;

    Assessment(String assessmentName) {
        this.assessmentName = assessmentName;
    }

    public String toLabel() {
        return assessmentName;
    }
}
