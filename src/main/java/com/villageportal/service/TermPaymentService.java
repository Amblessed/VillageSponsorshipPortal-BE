package com.villageportal.service;



/*
 * @Project Name: VillageSponsorshipPortal
 * @Author: Okechukwu Bright Onwumere
 * @Created: 12-Oct-25
 */


import com.villageportal.entity.Pupil;
import com.villageportal.entity.TermPayment;
import com.villageportal.exception.ResourceNotFoundException;
import com.villageportal.repository.PupilRepository;
import com.villageportal.repository.TermPaymentRepository;
import com.villageportal.request.TermPaymentRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TermPaymentService {

    private final PupilRepository pupilRepository;
    private  final TermPaymentRepository termPaymentRepository;

    public Pupil saveTermPayment(String firstName, String lastName, LocalDate birthDate, TermPaymentRequestDTO termPaymentDTO) {
        Pupil existingPupil = pupilRepository.findByFirstNameAndLastNameAndBirthDate(firstName, lastName, birthDate)
                .orElseThrow(() -> new ResourceNotFoundException("Pupil not found"));
        if (termPaymentDTO==null) {
            throw new IllegalArgumentException("No term payment provided");
        }

        existingPupil.addTermPayment(createTermPayment(termPaymentDTO));
        return pupilRepository.save(existingPupil);
    }


    private TermPayment createTermPayment(TermPaymentRequestDTO termPayment) {
        TermPayment payment = new TermPayment();
        payment.setTerm(termPayment.getTerm());
        payment.setClassLevel(termPayment.getClassLevel());
        payment.setTotalPaid(termPayment.getTotalPaid());
        payment.setFullyPaid(termPayment.isFullyPaid());
        return payment;
    }
}
