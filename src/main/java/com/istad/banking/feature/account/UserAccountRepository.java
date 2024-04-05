package com.istad.banking.feature.account;

import com.istad.banking.domain.UsersAccounts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UsersAccounts, Long> {
}

