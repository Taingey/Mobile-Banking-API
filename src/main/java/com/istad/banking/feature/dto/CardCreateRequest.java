package com.istad.banking.feature.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record CardCreateRequest(
        @NotBlank
        Long Id,
        String cardNumber,
        String cvv,
        String holderName,
        LocalDate expiredAt

) {
}
