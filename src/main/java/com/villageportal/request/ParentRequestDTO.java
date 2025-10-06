package com.villageportal.request;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 30-Sep-25
 */

import com.villageportal.entity.PersonDetails;
import lombok.Data;

@Data
public class ParentRequestDTO {

    private PersonDetails father;
    private PersonDetails mother;

    private int numberOfChildren;

    private Boolean parentsDivorced;
}
