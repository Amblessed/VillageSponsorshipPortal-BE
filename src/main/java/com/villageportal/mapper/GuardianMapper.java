package com.villageportal.mapper;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 03-Oct-25
 */


import com.villageportal.entity.Guardian;
import com.villageportal.request.GuardianRequestDTO;
import org.springframework.stereotype.Component;


@Component
public class GuardianMapper {

    public Guardian toEntity(GuardianRequestDTO dto) {
        Guardian guardian = new Guardian();

        guardian.setFirstName(dto.getFirstName());
        guardian.setMiddleName(dto.getMiddleName());
        guardian.setLastName(dto.getLastName());
        guardian.setOccupation(dto.getOccupation());
        guardian.setPhoneNumber(dto.getPhoneNumber());
        guardian.setAddress(dto.getAddress());
        guardian.setRelationship(dto.getRelationship());

        return guardian;
    }
}
