package com.istad.banking.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Account owner;

    @ManyToOne
    private Account transferReceiver;

    private String paymentReceiver;

    private BigDecimal amount;

    @Column(columnDefinition = "TEXT")
    private String remark;

    @Column(nullable = false, length = 30)
    private String transactionType;

    private Boolean status;

    private LocalDateTime transactionAt;

}
