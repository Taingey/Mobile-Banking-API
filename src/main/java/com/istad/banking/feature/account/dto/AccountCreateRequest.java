package com.istad.banking.feature.account.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record AccountCreateRequest(
        @NotBlank(message = "Alias is required")
        String alias,
        @NotNull(message = "First balance is required (5$ up)")
        BigDecimal balance,
        @NotBlank(message = "Account type alias is required")
        String accountTypeAlias,
        @NotBlank(message = "User owner is required")
        String userUuid,
        @NotEmpty
        String cardNumber
) {
}

