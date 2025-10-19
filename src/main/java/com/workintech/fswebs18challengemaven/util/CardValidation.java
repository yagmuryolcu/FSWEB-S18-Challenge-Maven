package com.workintech.fswebs18challengemaven.util;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.entity.Color;
import com.workintech.fswebs18challengemaven.entity.Type;
import com.workintech.fswebs18challengemaven.exceptions.CardException;
import org.springframework.http.HttpStatus;

public class CardValidation {

    public static void validateCard(Card card) {
        if (card == null) {
            throw new CardException("Card cannot be null", HttpStatus.BAD_REQUEST);
        }

        if (card.getType() == Type.JOKER) {
            if (card.getValue() != null || card.getColor() != null) {
                throw new CardException(
                        "JOKER card cannot have value or color",
                        HttpStatus.BAD_REQUEST
                );
            }
            return;
        }

        if (card.getType() != null && card.getValue() != null) {
            throw new CardException(
                    "Card cannot have both type and value",
                    HttpStatus.BAD_REQUEST
            );
        }

        if (card.getType() == null && card.getValue() == null) {
            throw new CardException(
                    "Card must have either type or value",
                    HttpStatus.BAD_REQUEST
            );
        }

        if (card.getValue() != null) {
            if (card.getValue() < 1 || card.getValue() > 13) {
                throw new CardException(
                        "Card value must be between 1 and 13",
                        HttpStatus.BAD_REQUEST
                );
            }
            if (card.getColor() == null) {
                throw new CardException(
                        "Card with value must have a color",
                        HttpStatus.BAD_REQUEST
                );
            }
        }

        if (card.getType() != null && card.getColor() == null) {
            throw new CardException(
                    "Card with type must have a color",
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    public static void validateId(Long id) {
        if (id == null || id <= 0) {
            throw new CardException("Invalid card ID", HttpStatus.BAD_REQUEST);
        }
    }

    public static void validateColor(String color) {
        if (color == null || color.trim().isEmpty()) {
            throw new CardException("Color cannot be null or empty", HttpStatus.BAD_REQUEST);
        }

        try {
            Color.valueOf(color.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new CardException(
                    "Invalid color. Must be one of: SPADE, HEART, DIAMOND, CLUB",
                    HttpStatus.BAD_REQUEST
            );
        }
    }

        public static void validateType(String type) {
        if (type == null || type.trim().isEmpty()) {
            throw new CardException("Type cannot be null or empty", HttpStatus.BAD_REQUEST);
        }

        try {
            Type.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new CardException(
                    "Invalid type. Must be one of: JACK, QUEEN, KING, ACE, JOKER",
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    public static void validateValue(Integer value) {
        if (value == null) {
            throw new CardException("Value cannot be null", HttpStatus.BAD_REQUEST);
        }

        if (value < 1 || value > 13) {
            throw new CardException(
                    "Value must be between 1 and 13",
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}