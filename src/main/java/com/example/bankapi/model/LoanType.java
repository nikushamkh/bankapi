package com.example.bankapi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Data
@Getter
@Setter
public class LoanType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long typeId;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal maxAmount;

    @Column(nullable = false)
    private Integer minTerm;

    @Column(nullable = false)
    private Integer maxTerm;
}
