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

    @Column(length = 50)
    private String name;

    @Column(length = 8)
    private String gender;

    @Column(unique = true, nullable = false, length = 15)
    private String uuid;

    @Column(unique = true)
    private String onSignalId;

    @Column(unique = true)
    private boolean isStudentCard;

    private Boolean isStudent;
    private Boolean isDeleted;

    @OneToMany(mappedBy = "user")
    private List<UsersAccounts> usersAccounts;

    @OneToMany(mappedBy = "user")
    private List<UsersRoles> usersRoles;

}
