package com.villageportal.entity;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 30-Sep-25
 */

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
    @AttributeOverride(name = "phone", column = @Column(name = "mother_phone", nullable = false))
    @AttributeOverride(name = "occupation", column = @Column(name = "mother_occupation", nullable = false))
    @AttributeOverride(name = "alive", column = @Column(name = "mother_alive"))
    private PersonDetails mother;

    private Boolean parentsDivorced = false;
    private int numberOfChildren;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Pupil> pupils = new HashSet<>();


    public void addPupil(Pupil pupil) {
        pupils.add(pupil);
        pupil.setParent(this);
    }

    public String getFatherFullName() {
        return father != null ? father.getFullName() : "";
    }

    public String getMotherFullName() {
        return mother != null ? mother.getFullName() : "";
    }





}
