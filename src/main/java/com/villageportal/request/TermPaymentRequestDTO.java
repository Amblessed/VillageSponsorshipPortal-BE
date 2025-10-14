package com.villageportal.request;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 05-Oct-25
 */


import com.villageportal.entity.Installment;
import com.villageportal.enums.ClassLevel;
import com.villageportal.enums.Term;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;


@Data
public class TermPaymentRequestDTO {


    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private ClassLevel classLevel;
    private Term term;
    private int totalPaid = 0;
    private boolean fullyPaid = false;
    private List<Installment> installments;

}
