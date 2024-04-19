package com.istad.banking.mapper;

import com.istad.banking.domain.Account;
import com.istad.banking.domain.User;
import com.istad.banking.domain.UsersAccounts;
import com.istad.banking.feature.account.dto.AccountCreateRequest;
import com.istad.banking.feature.account.dto.AccountResponse;
import com.istad.banking.feature.account.dto.AccountSnippetResponse;
import com.istad.banking.feature.user.dto.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        UserMapper.class,
        AccountTypeMapper.class
})
public interface AccountMapper {
    Account fromAccountCreateRequest(AccountCreateRequest accountCreateRequest);
    @Mapping(source = "usersAccounts", target = "user", qualifiedByName = "mapUserResponse")
    AccountResponse toAccountResponse(Account account);
    @Named("mapUserResponse")
    default UserResponse mapUserResponse(List<UsersAccounts> userAccountList){
        return toUserResponse(userAccountList.get(0).getUser());
    }
    UserResponse toUserResponse(User user);
    AccountSnippetResponse toAccountSnippetResponse(Account account);
}