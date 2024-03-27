package com.istad.banking.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "account_type")
public class AccountType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private boolean isDeleted;

    private Timestamp createdAt;

    private BigInteger createdBy;

    private Timestamp updatedAt;

    private BigInteger updateBy;

    private String description;

    @OneToMany(mappedBy = "accountType")
    private List<Account> account;
}
