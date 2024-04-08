package com.istad.banking.feature.account.dto;

import java.math.BigDecimal;

public record LimitTransactionAmountResponse(
        String accountNo,
        BigDecimal transactionLimit
) {
}
