package com.istad.banking.feature.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record AccountCreateRequest(
        @NotBlank
        String uuid,
        String accountName,
        @Max(5)
        String accountLimit,
        String accountTypeAlias,
        BigDecimal initialDeposit
) {
}
