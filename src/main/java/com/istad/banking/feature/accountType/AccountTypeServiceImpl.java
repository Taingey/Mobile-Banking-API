package com.istad.banking.feature.accountType;

import com.istad.banking.domain.AccountType;
import com.istad.banking.feature.accountType.dto.AccountTypeRequest;
import com.istad.banking.feature.accountType.dto.AccountTypeResponse;
import com.istad.banking.mapper.AccountTypeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j

public class AccountTypeServiceImpl implements AccountTypeService {
    private final AccountTypeRepository accountTypeRepository;
    private final AccountTypeMapper accountTypeMapper;

    @Override
    public void createAccountType(AccountTypeRequest accountTypeRequest) {
        AccountType accountType = new AccountType();
        accountType.setAlias(accountTypeRequest.alias());
        accountType.setName(accountTypeRequest.name());
        accountType.setDescription(accountTypeRequest.description());

        try {
            accountTypeRepository.save(accountType);
            log.info("Account type created successfully with alias: {}", accountType.getAlias());
        } catch (Exception e) {
            log.error("Failed to create account type: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create account type", e);
        }
    }



    @Override
    public List<AccountTypeResponse> findList() {
        List<AccountType> accountTypes = accountTypeRepository.findAll();
        return accountTypeMapper.toAccountTypeResponseList(accountTypes);
    }

    @Override
    public AccountTypeResponse findByAlias(String alias) {
        AccountType accountType = accountTypeRepository.findByAlias(alias)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Account type has not been found!"));

        return accountTypeMapper.toAccountTypeResponse(accountType);
    }

}
