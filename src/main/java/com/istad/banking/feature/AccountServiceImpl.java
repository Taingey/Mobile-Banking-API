package com.istad.banking.feature;

import com.istad.banking.domain.AccountType;
import com.istad.banking.feature.dto.AccountTypeResponse;
import com.istad.banking.feature.repository.AccountTypeRepository;
import com.istad.banking.feature.services.AccountTypeService;
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

public class AccountServiceImpl implements AccountTypeService {
    private final AccountTypeRepository accountTypeRepository;
    private final AccountTypeMapper accountTypeMapper;

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