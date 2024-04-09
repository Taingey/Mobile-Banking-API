package com.istad.banking.mapper;

import com.istad.banking.domain.Transactions;
import com.istad.banking.feature.transaction.dto.TransactionCreateRequest;
import com.istad.banking.feature.transaction.dto.TransactionResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = AccountMapper.class)
public interface TransactionMapper {
    TransactionResponse toTransactionResponse(Transactions transactions);

    Transactions fromTransactionsCreateRequest(TransactionCreateRequest transactionCreateRequest);
}
