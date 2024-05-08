package com.example.bankapi.controller;


import com.example.bankapi.model.Loan;
import com.example.bankapi.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping
    public ResponseEntity<Loan> createLoan(@RequestBody Loan loan){
        Loan savedLoan = loanService.createOrUpdateLoan(loan);
        return ResponseEntity.ok().body(savedLoan);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Loan> getLoanById(@PathVariable Long id){
        Loan loan = loanService.getLoanById(id);
        if(loan != null){
            return ResponseEntity.ok(loan);

        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
