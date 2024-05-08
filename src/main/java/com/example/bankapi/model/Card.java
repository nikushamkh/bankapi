package com.example.bankapi.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Data
@Getter
@Setter
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(nullable = false)
    private String cardNumber;

    @Column(nullable = false)
    private String cardHolderName;


    @Column(nullable = false)
    private Date validThrough;

    @Column(nullable = false)
    private String cvc;

    @Column(nullable = false)
    private String cardType;

    @Column(nullable = false)
    private boolean isActive;

    public void setIsActive(boolean b) {
        
    }
}
