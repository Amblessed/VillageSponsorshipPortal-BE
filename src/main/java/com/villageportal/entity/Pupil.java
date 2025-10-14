package com.villageportal.entity;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 30-Sep-25
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.villageportal.enums.ClassLevel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Pupil extends Person{

    @Enumerated(EnumType.STRING)
    private ClassLevel classLevel;

    @Column(length = 50000)
    private String story;

    @Lob
    @JsonIgnore
    private byte[] profileImage;

    @Column(name = "sponsored", nullable = false)
    private boolean sponsored = false;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;

    @ManyToOne
    private Guardian guardian;

    private boolean hasLivingParents;

    @ManyToOne
    private SchoolClass schoolClass;

    @OneToMany(mappedBy = "pupil", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<TermPayment> payments = new ArrayList<>();

    @OneToMany(mappedBy = "pupil", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Grade> grades = new ArrayList<>();

    @OneToMany(mappedBy = "pupil", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<ClassProgression> progressionHistory = new ArrayList<>();

    public void addClassProgression(ClassProgression progression) {
        progressionHistory.add(progression);
        progression.setPupil(this);
    }

    public void addTermPayment(TermPayment payment) {
        payments.add(payment);
        payment.setPupil(this);
    }

    public void addGrade(Grade grade) {
        grades.add(grade);
        grade.setPupil(this);
    }

    public String getFormattedRegistrationDate() {
        try {
            LocalDateTime createdAt = getCreatedAt();
            if (createdAt == null) {
                return "Not available";
            }

            int day = createdAt.getDayOfMonth();
            String suffix = getDaySuffix(day);
            String monthYear = createdAt.format(DateTimeFormatter.ofPattern("MMMM, yyyy"));

            return day + suffix + " " + monthYear;
        } catch (Exception e) {
            return "Not available";
        }
    }

    private String getDaySuffix(int day) {
        if (day >= 11 && day <= 13) {
            return "th";
        }
        return switch (day % 10) {
            case 1 -> "st";
            case 2 -> "nd";
            case 3 -> "rd";
            default -> "th";
        };
    }

    public void updateClassLevelFromProgression() {
        if (progressionHistory == null || progressionHistory.isEmpty()) return;

        progressionHistory.stream()
                .filter(p -> p.getStartDate() != null)
                .max(Comparator.comparing(ClassProgression::getStartDate)).ifPresent(latest -> this.classLevel = latest.getClassLevel());
    }

    public void promoteToNextClass(ClassLevel nextLevel, LocalDate startDate) {
        ClassProgression progression = new ClassProgression();
        progression.setClassLevel(nextLevel);
        progression.setStartDate(startDate);
        this.addClassProgression(progression);
        this.updateClassLevelFromProgression();
    }

    @Override
    public String toString() {
        return "Pupil{" +
                "id=" + getId() +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", birthDate=" + getBirthDate() +
                ", gender=" + getGender() +
                ", village=" + getVillage() +
                ", classLevel=" + classLevel +
                ", story='" + story + '\'' +
                ", sponsored=" + sponsored +
                ", parent=" + parent +
                ", guardian=" + guardian +
                ", hasLivingParents=" + hasLivingParents +
                ", schoolClass=" + schoolClass +
                ", payments=" + payments +
                '}';
    }

}
