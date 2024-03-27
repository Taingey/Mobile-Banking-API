package com.istad.banking.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer cardNo;
    private Integer cardCvv;
    @Column(unique = true, nullable = false, length = 9)
    private Timestamp cardExpiration;

    @ManyToOne
    private Account account;

    @ManyToOne
    private CardType cardType;
}
