package com.workintech.fswebs18challengemaven.controller;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.repository.CardRepository;
import com.workintech.fswebs18challengemaven.repository.CardRepositoryImpl;
import com.workintech.fswebs18challengemaven.util.CardValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/cards")
public class CardController {

    private CardRepository cardRepository;

    @Autowired
    public CardController(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @GetMapping
    public List<Card> getAllCards() {
        log.info("Fetching all cards");
        return cardRepository.findAll();
    }

    @GetMapping("/byColor/{color}")
    public List<Card> getCardsByColor(@PathVariable String color) {
        log.info("Fetching cards by color: {}", color);
        CardValidation.validateColor(color);
        return cardRepository.findByColor(color.toUpperCase());
    }

    @GetMapping("/byValue/{value}")
    public List<Card> getCardsByValue(@PathVariable Integer value) {
        log.info("Fetching cards by value: {}", value);
        CardValidation.validateValue(value);
        return cardRepository.findByValue(value);
    }

    @GetMapping("/byType/{type}")
    public List<Card> getCardsByType(@PathVariable String type) {
        log.info("Fetching cards by type: {}", type);
       // CardValidation.validateType(type);
        return cardRepository.findByType(type.toUpperCase());
    }

    @PostMapping
    public Card createCard(@RequestBody Card card) {
        log.info("Creating new card");
        CardValidation.validateCard(card);
        return cardRepository.save(card);
    }

    @PutMapping
    public Card updateCard(@RequestBody Card card) {
        log.info("Updating card: {}", card.getId());
        CardValidation.validateId(card.getId());
        CardValidation.validateCard(card);
        return cardRepository.update(card);
    }

    @DeleteMapping("/{id}")
    public Card deleteCard(@PathVariable Long id) {
        log.info("Deleting card: {}", id);
        CardValidation.validateId(id);
        return cardRepository.remove(id);
    }
}