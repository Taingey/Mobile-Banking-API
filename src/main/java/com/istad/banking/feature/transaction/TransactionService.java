package com.istad.banking.feature.transaction;

import com.istad.banking.feature.transaction.dto.TransactionCreateRequest;
import com.istad.banking.feature.transaction.dto.TransactionResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TransactionService {
    TransactionResponse transfer(TransactionCreateRequest transactionCreateRequest);

    Page<TransactionResponse> getTransactionHistorySortedByDate(int page, int size);
}
