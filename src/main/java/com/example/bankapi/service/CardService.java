package com.example.bankapi.service;

import com.example.bankapi.model.Card;

public interface CardService {
    Card orderCard(Long accountId, String cardType);
    Card activateCard(Long cardId);
}
