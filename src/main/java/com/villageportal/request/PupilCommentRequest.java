package com.villageportal.request;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 17-Oct-25
 */


import lombok.Data;

import java.util.List;

@Data
public class PupilCommentRequest {

    private String name;
    private String term;
    private List<String> subjects;
    private List<String> scores;
    private List<String> descriptors;
}
