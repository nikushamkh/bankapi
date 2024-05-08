package com.example.bankapi.model;

import com.example.bankapi.model.LoanType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@Data
public class InterestRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rateId;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private LoanType loanType;

    @Column(nullable = false)
    private BigDecimal rate;

    @Column(nullable = false)
    private Date validFrom;

    @Column(nullable = false)
    private Date validTo;

}
