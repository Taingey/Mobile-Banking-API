package com.istad.banking.feature.transaction.dto;

import com.istad.banking.feature.account.dto.AccountSnippetResponse;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionResponse(
        AccountSnippetResponse owner,
        AccountSnippetResponse transferReceiver,
        BigDecimal amount,
        String remark,
        String transactionType,
        String status,
        LocalDate transactionAt
) {
}
