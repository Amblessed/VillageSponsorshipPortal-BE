package com.villageportal.entity;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 02-Oct-25
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.villageportal.enums.ClassLevel;
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

    @Column(nullable = false)
    private int termNumber; // 1, 2, or 3

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClassLevel classLevel; // e.g., Primary 1, 2, etc.

    @Column(nullable = false)
    private int totalPaid = 0;

    @Column(nullable = false)
    private boolean fullyPaid = false;

    @OneToMany(mappedBy = "termPayment", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Installment> installments;

    private LocalDate paymentDate;

    @PrePersist
    private void prePersist() {
        this.paymentDate = LocalDate.now();
    }

    @Override
    public String toString() {
        return "TermPayment [id=" + id + ", termNumber=" + termNumber + ", classLevel=" + classLevel + ", totalPaid=" + totalPaid + ", fullyPaid=" + fullyPaid + ", installments=" + installments + ", paymentDate=" + paymentDate + "]";
    }

}
