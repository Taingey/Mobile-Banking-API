package com.istad.banking.feature.account;


import com.istad.banking.domain.Account;
import com.istad.banking.domain.AccountType;
import com.istad.banking.domain.User;
import com.istad.banking.domain.UsersAccounts;
import com.istad.banking.feature.account.dto.AccountCreateRequest;
import com.istad.banking.feature.account.dto.AccountResponse;
import com.istad.banking.feature.accountType.AccountTypeRepository;
import com.istad.banking.feature.accountType.dto.AccountTypeResponse;
import com.istad.banking.feature.user.UserRepository;
import com.istad.banking.feature.user.dto.UserResponse;
import com.istad.banking.mapper.AccountMapper;
import com.istad.banking.mapper.AccountTypeMapper;
import com.istad.banking.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor

public class AccountServiceImpl implements AccountService{
    private final UserAccountRepository userAccountRepository;
    private final AccountRepository accountRepository;
    private final AccountTypeRepository accountTypeRepository;
    private final UserRepository userRepository;
    private final AccountMapper accountMapper;
    private final AccountTypeMapper accountTypeMapper;
    private final UserMapper userMapper;
    @Override
    public void createNew(AccountCreateRequest accountCreateRequest) {

        AccountType accountType = accountTypeRepository.findByAlias(accountCreateRequest.accountTypeAlias())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Invalid account type"));

        User user = userRepository.findByUuid(accountCreateRequest.userUuid())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "User has not been found"));

        Account account = accountMapper.fromAccountCreateRequest(accountCreateRequest);
        account.setAccountType(accountType);
        account.setAccountName(user.getName());
        account.setAccountNo("123456789");
        account.setTransferLimit(BigDecimal.valueOf(5000));
        account.setIsHidden(false);

        // Save the account first
        Account savedAccount = accountRepository.save(account);

        UsersAccounts userAccount = new UsersAccounts();
        userAccount.setAccount(savedAccount); // Use the saved account
        userAccount.setUser(user);
        userAccount.setIsDeleted(false);
        userAccount.setIsBlocked(false);
        userAccount.setCreatedAt(LocalDateTime.now());

        userAccountRepository.save(userAccount);
    }


    @Override
    public AccountResponse findAccByActNo(String accountNo) {
        Account account = accountRepository.findByAccountNo(accountNo).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Account with ActNo not found. Please try again."));

        User user = account.getUsersAccounts().stream().findFirst()
                .map(UsersAccounts::getUser)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User associated with Account not found."));
        log.info(account.getUsersAccounts().toString());
        AccountTypeResponse accountTypeResponse = accountTypeMapper.toAccountTypeResponse(account.getAccountType());

        AccountResponse accountResponse = accountMapper.toAccountResponse(account);

        UserResponse userResponse = userMapper.toUserResponse(user);
        accountResponse = new AccountResponse(
                accountResponse.accountNo(),
                accountResponse.accountName(),
                accountResponse.alias(),
                accountResponse.balance(),
                accountTypeResponse,
                userResponse
        );

        return accountResponse;
    }

}
