package com.villageportal.mapper;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 30-Sep-25
 */


import com.villageportal.entity.Parent;
import com.villageportal.entity.PersonDetails;
import com.villageportal.request.ParentRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class ParentMapper {

    public Parent toEntity(ParentRequestDTO dto) {
        Parent parent = new Parent();
        
        // Create and set father details
        PersonDetails father = new PersonDetails();
        father.setFirstName(dto.getFather().getFirstName());
        father.setMiddleName(dto.getFather().getMiddleName());
        father.setLastName(dto.getFather().getLastName());
        father.setPhone(dto.getFather().getPhone());
        father.setOccupation(dto.getFather().getOccupation());
        father.setAlive(dto.getFather().getAlive());
        parent.setFather(father);

        // Create and set mother details
        PersonDetails mother = new PersonDetails();
        mother.setFirstName(dto.getMother().getFirstName());
        mother.setMiddleName(dto.getMother().getMiddleName());
        mother.setLastName(dto.getMother().getLastName());
        mother.setPhone(dto.getMother().getPhone());
        mother.setOccupation(dto.getMother().getOccupation());
        mother.setAlive(dto.getMother().getAlive());
        parent.setMother(mother);

        parent.setParentsDivorced(dto.getParentsDivorced());

        parent.setNumberOfChildren(dto.getNumberOfChildren());
        return parent;
    }
}
