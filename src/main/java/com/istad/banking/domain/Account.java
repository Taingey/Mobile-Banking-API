package com.istad.banking.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 9)
    private String accountNo;

    @Column(unique = true, nullable = false, length = 100)
    private String accountName;

    @Column(nullable = false)
    private BigDecimal transferLimit;

    @Column(length = 100)
    private String alias;

    @Column(nullable = false)
    private BigDecimal balance;

    @ManyToOne
    private AccountType accountType;

    @OneToMany(mappedBy = "account")
    private List<UsersAccounts> usersAccounts;

    @OneToOne
    private Card card;

    private Boolean isHidden;
}
