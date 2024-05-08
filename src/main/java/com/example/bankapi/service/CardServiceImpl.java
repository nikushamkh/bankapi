package com.example.bankapi.service;

import com.example.bankapi.model.Account;
import com.example.bankapi.model.Card;
import com.example.bankapi.repository.AccountRepository;
import com.example.bankapi.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository, AccountRepository accountRepository) {
        this.cardRepository = cardRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public Card orderCard(Long accountId, String cardType) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
        Card card = new Card();
        card.setAccount(account);
        card.setCardType(cardType);
        card.setCardHolderName(account.getOwnerName());
        card.setCardNumber(generateCardNumber(cardType));
        card.setValidThrough(calculateExpiryDate());
        card.setCvc(generateCVC());
        card.setIsActive(false);
        return cardRepository.save(card);
    }

    @Override
    @Transactional
    public Card activateCard(Long cardId) {
        Card card = cardRepository.findById(cardId).orElseThrow(() -> new RuntimeException("Card not found"));
        card.setIsActive(true);
        return cardRepository.save(card);
    }

    private String generateCardNumber(String cardType) {
        // Generate card number based on card type
        return "GeneratedCardNumber";
    }

    private Date calculateExpiryDate() {
        // Calculate card expiry date
        return new Date();
    }

    private String generateCVC() {
        // Generate a random CVC
        return "123";
    }
}
