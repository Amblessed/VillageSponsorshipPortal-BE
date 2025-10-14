package com.villageportal.entity;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 30-Sep-25
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@NoArgsConstructor
@Getter
@Setter
public class Parent{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private PersonDetails father;

    @Embedded
    @AttributeOverride(name = "firstName", column = @Column(name = "mother_first_name", nullable = false))
    @AttributeOverride(name = "middleName", column = @Column(name = "mother_middle_name"))
    @AttributeOverride(name = "lastName", column = @Column(name = "mother_last_name", nullable = false))
    @AttributeOverride(name = "phone", column = @Column(name = "mother_phone"))
    @AttributeOverride(name = "occupation", column = @Column(name = "mother_occupation"))
    @AttributeOverride(name = "alive", column = @Column(name = "mother_alive"))
    private PersonDetails mother;

    private Boolean parentsDivorced = false;
    private int numberOfChildren;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Pupil> pupils = new HashSet<>();


    public void addPupil(Pupil pupil) {
        pupils.add(pupil);
        pupil.setParent(this);
    }

    @PrePersist
    @PreUpdate
    private void sanitizeDeceasedParentFields() {
        if (father != null && Boolean.FALSE.equals(father.getAlive())) {
            father.setOccupation(null);
            father.setPhone(null);
        }

        if (mother != null && Boolean.FALSE.equals(mother.getAlive())) {
            mother.setOccupation(null);
            mother.setPhone(null);
        }
    }





}
