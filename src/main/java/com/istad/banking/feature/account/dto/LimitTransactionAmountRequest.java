package com.istad.banking.feature.account.dto;

import jakarta.validation.constraints.DecimalMin;

import java.math.BigDecimal;

public record LimitTransactionAmountRequest(
        @DecimalMin(value = "0.00", message = "Transaction limit must be greater than or equal to 0")
        BigDecimal transactionLimit
) {
}
