package com.example.bankapi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Data
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicantId;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private BigDecimal income;

    @Column(nullable = false)
    private Integer creditScore;


}
