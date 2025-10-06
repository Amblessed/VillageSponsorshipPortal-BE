package com.villageportal.entity;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 03-Oct-25
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.villageportal.enums.ClassLevel;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Entity
@Table(
        name = "school_class",
        uniqueConstraints = @UniqueConstraint(columnNames = {"classLevel", "academicYear"})
)
public class SchoolClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClassLevel classLevel; // e.g., "Primary_1"

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "schoolClass")
    @JsonIgnore
    private List<TermReport> termReports;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "schoolClass")
    @JsonIgnore
    private List<Pupil> pupils;

    @Column(nullable = false)
    private int academicYear; // e.g., 2025
}
