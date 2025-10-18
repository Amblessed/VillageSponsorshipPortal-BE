package com.villageportal.controller;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 18-Oct-25
 */



import com.villageportal.entity.Donation;
import com.villageportal.repository.DonationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donations")
@RequiredArgsConstructor
public class DonationController {

    private final DonationRepository donationRepository;

    @PostMapping
    public Donation submitDonation(@RequestBody Donation donation) {
        return donationRepository.save(donation);
    }

    @GetMapping("/total")
    public int getTotalDonated() {
        return donationRepository.getTotalDonated();
    }

    @GetMapping
    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }
}
