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

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
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

    public void addTermPayment(TermPayment payment) {
        payments.add(payment);
        payment.setPupil(this);
    }

    public String getFormattedRegistrationDate() {
        return getCreatedAt() != null ? getCreatedAt().toLocalDate().format(DateTimeFormatter.ofPattern("MMMM d, yyyy")) : "N/A";
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
                ", profileImage=" + Arrays.toString(profileImage) +
                ", sponsored=" + sponsored +
                ", parent=" + parent +
                ", guardian=" + guardian +
                ", hasLivingParents=" + hasLivingParents +
                ", schoolClass=" + schoolClass +
                ", payments=" + payments +
                '}';
    }

}
