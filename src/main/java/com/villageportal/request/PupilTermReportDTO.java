package com.villageportal.request;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 03-Oct-25
 */

import com.villageportal.enums.ClassLevel;
import com.villageportal.enums.Term;
import lombok.Data;

import java.util.Map;

@Data
public class PupilTermReportDTO {
    private Long pupilId;
    private String fullName;
    private ClassLevel classLevel;
    private int academicYear;

    private Map<Term, String> termSummaries;
}
