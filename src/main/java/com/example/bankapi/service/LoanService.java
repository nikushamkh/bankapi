package com.example.bankapi.service;


import com.example.bankapi.model.Loan;
import com.example.bankapi.repository.LoanRepository;
import com.example.bankapi.repository.LoanTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoanService {
    @Autowired
    private LoanRepository loanRepository;

    @Transactional
    public Loan createOrUpdateLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    @Transactional
    public Loan getLoanById(Long id) {
        return loanRepository.findById(id).orElse(null);
    }
}
