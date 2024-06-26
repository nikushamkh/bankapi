package com.example.bankapi.controller;


import com.example.bankapi.model.Account;
import com.example.bankapi.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account createdAccount = accountService.createAccount(account.getAccountNumber(), account.getOwnerName(), account.getBalance());
        return ResponseEntity.ok(createdAccount);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable Long id) {
        Account account = accountService.getAccount(id);
        if(account != null){
            return ResponseEntity.ok(account);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/withdraw")
    public ResponseEntity<Account>withdraw(@PathVariable Long id, @RequestParam BigDecimal amount){
        Account account = accountService.withdraw(id, amount);
        return ResponseEntity.ok(account);
    }

    @PostMapping("/{sourceId}/transfer/{destinationId}")
    public ResponseEntity<?> transferMoney(
            @PathVariable Long sourceId,
            @PathVariable Long destinationId,
            @RequestParam BigDecimal amount) {

        try {
            Account updatedSourceAccount = accountService.transfer(sourceId, destinationId, amount);
            return ResponseEntity.ok(updatedSourceAccount);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


}
