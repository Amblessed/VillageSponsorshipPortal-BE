package com.villageportal.entity;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 02-Oct-25
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Installment {

    @Id
    private Long id;

    @ManyToOne
    @JsonIgnore
    private TermPayment termPayment;

    @Positive(message = "Amount paid must be positive")
    private int amountPaid;

    private LocalDate paymentDate;
    private String paymentMethod; // optional: cash, transfer, etc.

    @PrePersist
    public void prePersist() {
        this.paymentDate = LocalDate.now();
    }
}
