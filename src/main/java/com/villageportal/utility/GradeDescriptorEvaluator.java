package com.villageportal.utility;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 12-Oct-25
 */


import com.villageportal.enums.Descriptor;

public class GradeDescriptorEvaluator {

    public static Descriptor getDescriptor(double score) {
        if (score >= 90) return Descriptor.EXCELLENT;
        if (score >= 80) return Descriptor.VERY_GOOD;
        if (score >= 70) return Descriptor.GOOD;
        if (score >= 60) return Descriptor.FAIR;
        if (score >= 50) return Descriptor.POOR;
        return Descriptor.FAIL;
    }
}
