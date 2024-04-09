package com.istad.banking.feature.transaction;

import com.istad.banking.domain.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transactions, Long> {
}
