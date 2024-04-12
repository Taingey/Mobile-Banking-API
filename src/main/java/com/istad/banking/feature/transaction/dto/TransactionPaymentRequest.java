package com.istad.banking.feature.transaction.dto;

import java.math.BigDecimal;

public record TransactionPaymentRequest(
        String ownerAccountNo,
        BigDecimal amount,
        String paymentReceiver,
        String remark
) {
}