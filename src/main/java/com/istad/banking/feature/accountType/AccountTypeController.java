package com.istad.banking.feature.accountType;

import com.istad.banking.feature.accountType.dto.AccountTypeRequest;
import com.istad.banking.feature.accountType.dto.AccountTypeResponse;
import com.istad.banking.feature.accountType.AccountTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/account-types")
@RequiredArgsConstructor
public class AccountTypeController {
    private final AccountTypeService accountTypeService;
    @GetMapping
    List<AccountTypeResponse> findList() {
        return accountTypeService.findList();
    }
    @GetMapping("/{alias}")
    AccountTypeResponse findByAlias(@PathVariable String alias) {
        return accountTypeService.findByAlias(alias);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void accountTypeCreate(@Valid @RequestBody AccountTypeRequest accountTypeRequest){
        accountTypeService.createAccountType(accountTypeRequest);
    }
}
