package com.istad.banking.feature.transaction;

import com.istad.banking.feature.transaction.dto.TransactionCreateRequest;
import com.istad.banking.feature.transaction.dto.TransactionPaymentRequest;
import com.istad.banking.feature.transaction.dto.TransactionResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;

public interface TransactionService {
    TransactionResponse transfer(TransactionCreateRequest transactionCreateRequest);
    Page<TransactionResponse> getTransactionHistorySortedByDate(int page, int size);

    void payment(TransactionPaymentRequest transactionPaymentRequest);
}
