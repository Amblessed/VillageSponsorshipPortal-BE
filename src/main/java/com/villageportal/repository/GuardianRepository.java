package com.villageportal.repository;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 03-Oct-25
 */


import com.villageportal.entity.Guardian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface GuardianRepository extends JpaRepository<Guardian, Long> {


    @Query("SELECT g FROM Guardian g WHERE g.firstName = :firstName AND g.lastName = :lastName AND g.phoneNumber = :phoneNumber")
    Optional<Guardian> findByGuardian(String firstName, String lastName, String phoneNumber);


}
