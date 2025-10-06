package com.villageportal.entity;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 30-Sep-25
 */


import com.villageportal.enums.PreferredGender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Sponsor extends Person{

    @Column(length = 25)
    private String email;

    @Column(length = 25, nullable = false)
    private String phoneNumber;

    @Min(value = 1000, message = "Monthly commitment must be at least 1000 Naira")
    private int monthlyCommitment;

    @Enumerated(EnumType.STRING)
    private PreferredGender preferredGender;


}
