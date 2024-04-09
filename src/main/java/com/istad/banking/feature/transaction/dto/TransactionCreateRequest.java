package com.istad.banking.feature.transaction.dto;

import com.istad.banking.feature.account.dto.AccountSnippetResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record TransactionCreateRequest(
        @NotBlank(message = "Transfer account no of owner is required")
        String ownerAccountNo,
        @NotBlank(message = "Transfer account no of receive is required")
        String transferReceiveAccountNo,
        @NotNull(message = "Amount is required")
        @Positive
        BigDecimal amount,
        String remark
) {
}
