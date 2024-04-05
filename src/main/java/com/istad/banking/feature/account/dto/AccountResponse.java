package com.istad.banking.feature.account.dto;

import com.istad.banking.feature.accountType.dto.AccountTypeResponse;
import com.istad.banking.feature.user.dto.UserResponse;

import java.math.BigDecimal;

public record AccountResponse(
        String alias,
        String accountName,
        String accountNo,
        BigDecimal balance,
        AccountTypeResponse accountType,
        UserResponse user
) {
}
