package com.istad.banking.mapper;


import com.istad.banking.domain.AccountType;
import com.istad.banking.feature.dto.AccountTypeResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountTypeMapper {
    AccountTypeResponse toAccountTypeResponse(AccountType accountType);

    List<AccountTypeResponse> toAccountTypeResponseList(List<AccountType> accountTypes);

}
