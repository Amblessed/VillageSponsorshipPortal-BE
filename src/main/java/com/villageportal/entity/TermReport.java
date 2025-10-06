package com.villageportal.entity;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 03-Oct-25
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.villageportal.enums.Term;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class TermReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Pupil pupil;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Term term; // TERM_ONE, TERM_TWO, TERM_THREE

    private String summary;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String teacherName;

    @ManyToOne
    @JsonIgnore
    private SchoolClass schoolClass;

    @PrePersist
    private void prePersist() {
        this.date = LocalDate.now();
    }
}
