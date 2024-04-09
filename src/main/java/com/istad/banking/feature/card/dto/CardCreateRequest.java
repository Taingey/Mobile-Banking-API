package com.istad.banking.feature.card.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record CardRequest(
        @NotBlank(message = "Card number is required")
        String cardNumber,
        @NotBlank(message = "CVV is required")
        String cvv,
        String holderName,
        LocalDate issuedAt,
        LocalDate expiredAt,
        Boolean isDeleted,
        Long cardTypeId
) {
}
