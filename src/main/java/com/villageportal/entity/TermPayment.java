package com.villageportal.entity;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 02-Oct-25
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.villageportal.enums.ClassLevel;
import com.villageportal.enums.Term;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class TermPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pupil_id", nullable = false)
    private Pupil pupil;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Term term;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClassLevel classLevel;

    @Column(nullable = false)
    private int totalPaid = 0;

    @Column(nullable = false)
    private boolean fullyPaid = false;

    private int expectedAmount=8000;

    @OneToMany(mappedBy = "termPayment", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Installment> installments;

    @Column(nullable = false)
    private LocalDate paymentDate;

    @PrePersist
    private void prePersist() {
        this.paymentDate = LocalDate.now();
        this.fullyPaid = getTotalPaid() >= expectedAmount;
    }

    @PreUpdate
    private void updateFullyPaidStatus() {
        this.fullyPaid = getTotalPaid() >= expectedAmount;
    }

    @Override
    public String toString() {
        return "TermPayment [id=" + id + ", termNumber=" + term + ", classLevel=" + classLevel + ", totalPaid=" + totalPaid + ", fullyPaid=" + fullyPaid + ", paymentDate=" + paymentDate + "]";
    }

}
