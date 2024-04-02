package com.istad.banking.feature.services;

import com.istad.banking.domain.Account;
import com.istad.banking.domain.Transactions;
import com.istad.banking.feature.dto.AccountCreateRequest;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    void createNewAccount(String userId, AccountCreateRequest request);
    Account accountDetails(String userId, String accountId, String accountName, String accountNo, BigDecimal accountBalance);
    List<Transactions> accountHistory(String userId, String accountId);
    void renameAccount(String userId, String accountId, String newName);
    void hideAccount(String userId, String accountId);
    void limitTransactionAccount(String userId, String accountId, BigDecimal limit);
}
