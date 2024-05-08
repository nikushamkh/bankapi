package com.example.bankapi.service;


import com.example.bankapi.model.Applicant;
import com.example.bankapi.repository.ApplicantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ApplicantService {

    @Autowired
    private ApplicantRepository applicantRepository;

    @Transactional
    public Applicant createOrUpdateApplicant(Applicant applicant) {
        return applicantRepository.save(applicant);
    }

    @Transactional(readOnly = true)
    public Applicant getApplicantById(Long id) {
        return applicantRepository.findById(id).orElse(null);
    }

}
