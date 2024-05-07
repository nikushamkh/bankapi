package com.example.bankapi.model;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String accountNumber;


    @Column(nullable=false)
    private String ownerName;

    @Column(nullable = false)
    private BigDecimal balance;
}
