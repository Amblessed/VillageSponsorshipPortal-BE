package com.villageportal.entity;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 11-Oct-25
 */

import com.villageportal.enums.ClassLevel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class ClassProgression {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pupil_id", nullable = false)
    private Pupil pupil;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClassLevel classLevel;

    @Column(nullable = false)
    private LocalDate startDate;

    private LocalDate endDate;

    private boolean repeated = false;

    private String notes; // Optional: e.g. "Transferred mid-term", "Promoted early
}
