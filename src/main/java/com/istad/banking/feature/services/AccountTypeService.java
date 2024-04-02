package com.istad.banking.feature.services;

import com.istad.banking.domain.AccountType;
import com.istad.banking.feature.dto.AccountTypeResponse;

import java.util.List;

public interface AccountTypeService {
    List<AccountTypeResponse> findList();
    AccountTypeResponse findByAlias(String alias);
}
