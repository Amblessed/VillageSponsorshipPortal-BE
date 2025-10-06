package com.villageportal.entity;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 30-Sep-25
 */


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Embeddable
public class PersonDetails {

    @Column(nullable = false, length = 25)
    private String firstName;

    @Column(length = 25)
    private String middleName;

    @Column(nullable = false, length = 25)
    private String lastName;

    @Column(nullable = false, length = 50)
    private String occupation;

    @Column(nullable = false, length = 15, unique = true)
    private String phone;

    private Boolean alive = true;

    public String getFullName() {
        return Stream.of(firstName, middleName, lastName)
                .filter(Objects::nonNull)
                .collect(Collectors.joining(" "));
    }
}
