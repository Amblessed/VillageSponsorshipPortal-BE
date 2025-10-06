package com.villageportal.repository;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 30-Sep-25
 */


import com.villageportal.entity.Pupil;
import com.villageportal.enums.ClassLevel;
import com.villageportal.enums.Gender;
import com.villageportal.enums.Village;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PupilRepository extends JpaRepository<Pupil, Long> {

    List<Pupil> findByVillage(Village village);

    List<Pupil> findBySponsoredFalse(Pageable pageable);

    @Query("SELECT p FROM Pupil p WHERE YEAR(CURRENT_DATE) - YEAR(p.birthDate) BETWEEN :minAge AND :maxAge")
    List<Pupil> findByAgeRange(@Param("minAge") int minAge, @Param("maxAge") int maxAge);

    @Query("SELECT p FROM Pupil p WHERE p.firstName = :firstName AND p.lastName = :lastName")
    Pupil findByFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);

    @Query("SELECT p FROM Pupil p WHERE p.sponsored = true")
    List<Pupil> findByIsSponsoredTrue();

    @Query("SELECT p FROM Pupil p WHERE p.sponsored = false AND p.classLevel = :classLevel")
    List<Pupil> findByIsSponsoredFalseAndClassLevel(@Param("classLevel") ClassLevel classLevel);

    @Query("SELECT p FROM Pupil p WHERE p.sponsored = false AND p.gender = :gender")
    List<Pupil> findByIsSponsoredFalseAndGender(@Param("gender") Gender gender);

    @Query("SELECT p FROM Pupil p WHERE p.sponsored = false AND p.village = :village")
    List<Pupil> findByIsSponsoredFalseAndVillage(@Param("village") Village village);

    @EntityGraph(attributePaths = {"payments", "guardian"})
    @Query("SELECT p FROM Pupil p WHERE p.firstName = :firstName AND p.lastName = :lastName AND p.birthDate = :birthDate")
    Optional<Pupil> findByFirstNameAndLastNameAndBirthDate(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("birthDate") LocalDate birthDate);

}
