package com.istad.banking.feature.accountType;

import com.istad.banking.domain.Account;
import com.istad.banking.domain.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountTypeRepository extends JpaRepository<AccountType, Integer> {
    Optional<AccountType> findByAlias(String alias);
}
