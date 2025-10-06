package com.villageportal.entity;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 02-Oct-25
 */


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.villageportal.enums.Relationship;
import jakarta.persistence.*;
import lombok.*;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Guardian {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 25)
    private String firstName;

    @Column(length = 25)
    private String middleName;

    @Column(nullable = false, length = 25)
    private String lastName;

    @Column(nullable = false, length = 25)
    private String occupation;

    @Column(nullable = false, length = 25)
    private String phoneNumber;

    @Column(nullable = false, length = 75)
    private String address;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Relationship relationship; // e.g., uncle, aunt, neighbor

    @JsonIgnore
    @OneToMany(mappedBy = "guardian")
    private List<Pupil> pupils;

    @Override
    public String toString() {
        return "Guardian [firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName
                + ", occupation=" + occupation + ", phoneNumber=" + phoneNumber + ", address=" + address + ", relationship=" + relationship + "]";
    }
}
