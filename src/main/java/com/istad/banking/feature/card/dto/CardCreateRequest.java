package com.istad.banking.feature.card.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CardCreateRequest(
        @NotBlank(message = "Card number is required")
        String cardNumber,
        @NotBlank(message = "CVV is required")
        String cvv,
        String holderName,
        LocalDate issuedAt,
        LocalDate expiredAt,
        @NotNull
        Boolean isDeleted,
        String cardTypeName
) {
}
