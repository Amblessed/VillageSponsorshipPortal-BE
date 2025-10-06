package com.villageportal.response;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 03-Oct-25
 */


import com.villageportal.enums.ClassLevel;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TermPaymentResponseDTO {

    private Long id;
    private int termNumber;
    private ClassLevel classLevel;
    private int totalPaid = 0;
    private LocalDate paymentDate;
    private boolean fullyPaid = false;
}
