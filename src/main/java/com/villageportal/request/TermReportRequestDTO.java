package com.villageportal.request;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 03-Oct-25
 */


import com.villageportal.enums.Term;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TermReportRequestDTO {

    @NotNull
    private Long pupilId;

    @NotNull
    private Term term; // TERM_ONE, TERM_TWO, TERM_THREE

    @NotBlank
    private String summary;

    @NotNull
    private LocalDate date;

    private String teacherName;
}
