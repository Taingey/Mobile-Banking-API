package com.istad.banking.feature.accountType;

import com.istad.banking.feature.account.dto.AccountCreateRequest;
import com.istad.banking.feature.accountType.dto.AccountTypeRequest;
import com.istad.banking.feature.accountType.dto.AccountTypeResponse;

import java.util.List;

public interface AccountTypeService {
    void createAccountType(AccountTypeRequest accountTypeRequest);
    List<AccountTypeResponse> findList();
    AccountTypeResponse findByAlias(String alias);
}
