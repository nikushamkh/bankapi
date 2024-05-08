package com.example.bankapi.service;

import com.example.bankapi.model.Account;
import com.example.bankapi.model.Transaction;
import com.example.bankapi.repository.AccountRepository;
import com.example.bankapi.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

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
        // You should change to findByAccountNumber if accountNumber is used, otherwise findById for accountId.
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
    public Account transfer(Long sourceId, Long destinationId, BigDecimal amount) throws Exception {
        Account sourceAccount = accountRepository.findById(sourceId)
                .orElseThrow(() -> new Exception("Source account not found"));
        Account destinationAccount = accountRepository.findById(destinationId)
                .orElseThrow(() -> new Exception("Destination account not found"));

        if (sourceAccount.getBalance().compareTo(amount) < 0) {
            throw new Exception("Insufficient funds in the source account");
        }

        sourceAccount.setBalance(sourceAccount.getBalance().subtract(amount));
        destinationAccount.setBalance(destinationAccount.getBalance().add(amount));

        accountRepository.save(sourceAccount);
        accountRepository.save(destinationAccount);
        Transaction transaction = new Transaction();
        transaction.setSourceAccount(sourceAccount);
        transaction.setDestinationAccount(destinationAccount);
        transaction.setAmount(amount);
        transaction.setTransactionDate(new Date());  // Current date and time
        transactionRepository.save(transaction);

        return sourceAccount;
    }
}
