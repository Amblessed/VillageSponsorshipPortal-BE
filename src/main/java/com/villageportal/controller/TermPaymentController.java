package com.villageportal.controller;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 05-Oct-25
 */

import com.villageportal.entity.TermPayment;
import com.villageportal.exception.ResourceNotFoundException;
import com.villageportal.repository.TermPaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {
        "http://localhost:3000",
        "https://village-sponsorship-portal-fe.vercel.app"
})
@RestController
@RequestMapping("/api/term-payments")
@RequiredArgsConstructor
public class TermPaymentController {

    private final TermPaymentRepository termPaymentRepository;


    @GetMapping("/{id}")
    public ResponseEntity<TermPayment> getTermPayment(@PathVariable("id") Long id) {
        System.out.println(id);
        TermPayment payment = termPaymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Term Payment not found"));
        return ResponseEntity.ok(payment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TermPayment> updateTermPayment(@PathVariable("id") Long id, @RequestBody TermPayment termPayment) {
        TermPayment payment = termPaymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Term Payment not found"));
        return ResponseEntity.ok(payment);
    }
}
