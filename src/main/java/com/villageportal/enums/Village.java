package com.villageportal.enums;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 30-Sep-25
 */

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.util.Arrays;


public enum Village {

    UMUNUMO("Umunumo"),
    UMUELEM("Umuelem"),
    UMUEYE("Umueye"),
    UMUDISHI("Umudishi"),
    UMUEZEALACHUKWU("Umuezealachukwu"),
    UMUDURUEZE("Umudurueze"),
    UMUNAMA("Umunama"),
    UMUDURU("Umuduru"),
    UMUOGWU("Umuogwu"),
    UMUONA("Umuona");

    private final String toVillage;

    Village(String toVillage) {
        this.toVillage = toVillage;
    }

    public String toLabel() {
        return toVillage;
    }

    @JsonCreator
    public static Village fromString(String value) {
        return Arrays.stream(Village.values())
                .filter(v -> v.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid village: " + value));
    }


}
