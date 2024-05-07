package com.example.bankapi.service;

import com.example.bankapi.model.Account;


import java.math.BigDecimal;

public interface AccountService {
    Account createAccount(String accountNumber, String ownerName, BigDecimal initialBalance);
    Account getAccount(Long accountId);
    Account deposit(Long accountId, BigDecimal amount);
    Account withdraw(Long accountId, BigDecimal amount);
    Account transfer(Long sourceAccountId, Long destinationAccountId, BigDecimal amount) throws Exception;  // Added this line
}
