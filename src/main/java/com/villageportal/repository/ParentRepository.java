package com.villageportal.repository;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 30-Sep-25
 */


import com.villageportal.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ParentRepository extends JpaRepository<Parent, Long> {


    @Query("SELECT p FROM Parent p WHERE p.father.lastName = :fatherLastName AND p.father.phone = :fatherPhone AND p.mother.firstName = :motherFirstName AND p.mother.phone = :motherPhone AND p.numberOfChildren = :numberOfChildren")
    Optional<Parent> findByParent(String fatherLastName, String fatherPhone, String motherFirstName, String motherPhone, int numberOfChildren);

}
