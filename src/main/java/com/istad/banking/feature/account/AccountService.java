package com.istad.banking.feature.account;

import com.istad.banking.feature.account.dto.AccountCreateRequest;
import com.istad.banking.feature.account.dto.AccountResponse;

public interface AccountService {
    void createNew(AccountCreateRequest accountCreateRequest);
    AccountResponse findAccByActNo(String accountNo);
}
