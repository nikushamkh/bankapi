package com.example.bankapi.controller;

import com.example.bankapi.model.Card;
import com.example.bankapi.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cards")
public class CardController {
    @Autowired
    private CardService cardService;

    @PostMapping("/order")
    public ResponseEntity<Card> orderCard(@RequestParam Long accountId, @RequestParam String cardType) {
        Card card = cardService.orderCard(accountId, cardType);
        return ResponseEntity.ok(card);
    }

    @PostMapping("/activate/{cardId}")
    public ResponseEntity<Card> activateCard(@PathVariable Long cardId) {
        Card card = cardService.activateCard(cardId);
        return ResponseEntity.ok(card);
    }
}
