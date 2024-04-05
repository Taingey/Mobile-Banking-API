package com.istad.banking.feature.account;


import com.istad.banking.feature.account.dto.AccountCreateRequest;
import com.istad.banking.feature.account.dto.AccountResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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


}
