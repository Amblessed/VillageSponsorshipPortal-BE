package com.villageportal.response;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 17-Oct-25
 */


import com.villageportal.enums.*;
import lombok.Data;



@Data
public class GradeResponse {


    private String subject;
    private String term;
    private String assessmentType;
    private String classLevel;
    private Double score;
    private GradeLetter gradeLetter;
    private String descriptor;
}
