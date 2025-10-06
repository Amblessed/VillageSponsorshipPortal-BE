package com.villageportal.enums;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 02-Oct-25
 */


public enum Term {

    FIRST_TERM("First-Term"),
    SECOND_TERM("Second-Term"),
    THIRD_TERM("Third-Term");

    private final String whichTerm;

    Term(String whichTerm) {
        this.whichTerm = whichTerm;
    }

    public String toLabel() {
        return whichTerm;
    }
}
