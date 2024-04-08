package com.istad.banking.feature.account;

import com.istad.banking.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository
        extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNo(String accountNo);
    @Query("""
    UPDATE Account AS a
    SET a.isHidden = true
    WHERE a.accountNo = ?1
""")
    void hideAccountByAccount(String accountNo);
    boolean existsByAccountNo(String accountNo);
}
