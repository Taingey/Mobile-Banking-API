package com.istad.banking.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String name;

    @Column(length = 20)
    private String gender;

    @Column(length = 15)
    private String uuid;

    @Column(unique = true)
    private boolean isDeleted;

    @Column(unique = true)
    private boolean isStudent;

    @OneToOne
    private UsersAccounts usersAccounts;
}
