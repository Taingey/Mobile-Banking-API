package com.istad.banking.feature.account;


import com.istad.banking.feature.account.dto.AccountCreateRequest;
import com.istad.banking.feature.account.dto.AccountRenameRequest;
import com.istad.banking.feature.account.dto.AccountResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createNew(@Valid @RequestBody AccountCreateRequest accountCreateRequest) {
        accountService.createNew(accountCreateRequest);
    }

    @GetMapping("{accountNo}")
    AccountResponse findByActNo(@PathVariable String accountNo) {
        return accountService.findAccByActNo(accountNo);
    }

    @PutMapping("/{accountNo}/rename")
    AccountResponse renameByAccountNo(@PathVariable String accountNo,
                                      @Valid @RequestBody AccountRenameRequest accountRenameRequest){
        return accountService.renameByAccountNo(accountNo, accountRenameRequest);
    }

    @PutMapping("/{accountNo}/hide")
    void hideAccountByAccountNo(@PathVariable String accountNo){
        accountService.hideAccount(accountNo);
    }

    @GetMapping
    public Page<AccountResponse> getAccounts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return accountService.findList(page,size);
    }

    @PutMapping("/{accountNo}/limit")
    void setTransactionLimit(@PathVariable String accountNo,
                             @RequestParam BigDecimal limit) {
        accountService.setTransactionLimit(accountNo, limit);
    }
    @GetMapping("/{accountNo}/transaction-limit")
    public BigDecimal getTransactionLimit(@PathVariable String accountNo) {
        return accountService.getTransactionLimit(accountNo);
    }
}