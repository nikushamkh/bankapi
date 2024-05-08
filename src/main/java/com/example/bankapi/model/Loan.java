package com.example.bankapi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@Data
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    @ManyToOne
    @JoinColumn(name = "applicant_id", nullable = false)
    private Applicant applicant;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private Integer term;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Date createdAt;

    @Column(nullable = false)
    private Date updatedAt;

    @OneToMany(mappedBy = "loan")
    private Set<Document> documents;

    @OneToMany(mappedBy = "loan")
    private Set<Payment> payments;

    @OneToMany(mappedBy = "loan")
    private Set<AuditLog> auditLogs;


}
