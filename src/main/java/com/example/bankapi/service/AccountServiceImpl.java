package com.example.bankapi.service;

import com.example.bankapi.model.Account;
import com.example.bankapi.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;


@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    @Transactional
    public Account createAccount(String accountNumber, String ownerName, BigDecimal initialBalance) {
        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setOwnerName(ownerName);
        account.setBalance(initialBalance);
        return accountRepository.save(account);
    }

    @Override
    public Account getAccount(Long accountId) {
        return accountRepository.findById(accountId).orElse(null);
    }

    @Override
    @Transactional
    public Account deposit(Long accountId, BigDecimal amount) {
        Account account = getAccount(accountId);
        if (account != null) {
            account.setBalance(account.getBalance().add(amount));
            accountRepository.save(account);
        }
        return account;
    }

    @Override
    @Transactional
    public Account withdraw(Long accountId, BigDecimal amount) {
        Account account = getAccount(accountId);
        if (account != null && account.getBalance().compareTo(amount) >= 0) {
            account.setBalance(account.getBalance().subtract(amount));
            accountRepository.save(account);
        }
        return account;
    }

    @Override
    @Transactional
    public Account transfer(Long sourceAccountId, Long destinationAccountId, BigDecimal amount) throws Exception {
        Account sourceAccount = getAccount(sourceAccountId);
        Account destinationAccount = getAccount(destinationAccountId);

        if (sourceAccount == null || destinationAccount == null) {
            throw new Exception("One or both accounts not found");
        }

        if (sourceAccount.getBalance().compareTo(amount) < 0) {
            throw new Exception("Insufficient funds");
        }

        sourceAccount.setBalance(sourceAccount.getBalance().subtract(amount));
        destinationAccount.setBalance(destinationAccount.getBalance().add(amount));

        accountRepository.save(sourceAccount);
        accountRepository.save(destinationAccount);

        return sourceAccount; // Returning the source account for consistency with your design.
    }

}
