package com.example.bankapi.controller;


import com.example.bankapi.model.Applicant;
import com.example.bankapi.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applicants")
public class ApplicantController {

    @Autowired
    private ApplicantService applicantService;

    @PostMapping
    public ResponseEntity<Applicant> createApplicant(@RequestBody Applicant applicant) {
        Applicant savedApplicant = applicantService.createOrUpdateApplicant(applicant);
        return ResponseEntity.ok(savedApplicant);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Applicant> getApplicant(@PathVariable Long id) {
        Applicant applicant = applicantService.getApplicantById(id);
        return ResponseEntity.ok(applicant);
    }


}
