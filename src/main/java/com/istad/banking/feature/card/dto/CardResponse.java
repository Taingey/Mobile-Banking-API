package com.istad.banking.feature.card.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record CardResponse(
        String cardNumber,
        String cvv,
        String holderName,
        LocalDate issuedAt,
        LocalDate expiredAt
) {
}
