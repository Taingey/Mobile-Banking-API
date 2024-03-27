package com.istad.banking.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountNo;

    private String accountName;

    @Column(length = 8)
    private String password;

    private String phoneNumber;

    private Integer transferLimit;

    @ManyToOne
    private AccountType accountType;
}
