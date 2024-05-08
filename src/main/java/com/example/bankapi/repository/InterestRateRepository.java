package com.example.bankapi.repository;

import com.example.bankapi.model.InterestRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestRateRepository extends JpaRepository<InterestRate, Long> {
}
