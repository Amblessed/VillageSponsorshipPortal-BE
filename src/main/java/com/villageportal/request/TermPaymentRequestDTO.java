package com.villageportal.request;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 05-Oct-25
 */


import com.villageportal.entity.Installment;
import com.villageportal.entity.Pupil;
import com.villageportal.enums.ClassLevel;
import lombok.Data;

import java.util.List;


@Data
public class TermPaymentRequestDTO {


    private Pupil pupil;
    private int termNumber; // 1, 2, or 3
    private ClassLevel classLevel; // e.g., Primary 1, 2, etc.
    private int totalPaid = 0;
    private boolean fullyPaid = false;
    private List<Installment> installments;

}
