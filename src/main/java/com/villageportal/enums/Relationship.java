package com.villageportal.enums;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 02-Oct-25
 */


public enum Relationship {

    UNCLE("Uncle"),
    AUNT("Aunt"),
    BROTHER("Brother"),
    SISTER("Sister"),
    NEIGHBOR("Neighbor"),
    COUSIN("Cousin"),
    GRANDMOTHER("Grandmother"),
    GRANDFATHER("Grandfather");


    private final String relationshipName;

    Relationship(String relationshipName) {
        this.relationshipName = relationshipName;
    }

    public String toLabel() {
        return relationshipName;
    }
}
