package com.istad.banking.feature.account;

import com.istad.banking.feature.account.dto.*;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    void createNew(AccountCreateRequest accountCreateRequest);
    AccountResponse findAccByActNo(String accountNo);
    AccountResponse renameByAccountNo(String accountNo, AccountRenameRequest accountRenameRequest);
    void hideAccount(String hide);
    Page<AccountResponse> findList(int page, int size);

    void setTransactionLimit(String accountNo, BigDecimal limit);
    BigDecimal getTransactionLimit(String accountNo);
}
