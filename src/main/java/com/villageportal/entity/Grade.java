package com.villageportal.entity;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 09-Oct-25
 */

import com.villageportal.enums.*;
import com.villageportal.utility.GradeDescriptorEvaluator;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"pupil_id", "subject", "term", "assessmentType", "classLevel"})
    })
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pupil_id")
    private Pupil pupil;

    @Column(nullable = false)
    private String subject;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Term term;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Assessment assessmentType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClassLevel classLevel;

    @Column(columnDefinition = "DOUBLE", nullable = false)
    @Min(value = 0, message = "Score cannot be less than 0")
    @Max(value = 100, message = "Score cannot be greater than 100")
    private Double score;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GradeLetter gradeLetter;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Descriptor descriptor;

    @Column(columnDefinition = "TEXT")
    private String teacherComment;

    @Column(nullable = false)
    private LocalDate date;

    @PrePersist
    private void prePersist() {
        this.date = LocalDate.now();
        if (assessmentType == Assessment.EXAM && score != null) {
            this.descriptor = GradeDescriptorEvaluator.getDescriptor(score);
        } else {
            this.descriptor = Descriptor.NOT_APPLICABLE;
            this.gradeLetter = GradeLetter.NOT_APPLICABLE;
        }
    }
}
