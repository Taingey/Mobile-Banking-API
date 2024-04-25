package com.istad.banking.init;

import com.istad.banking.domain.Authorities;
import com.istad.banking.domain.Roles;
import com.istad.banking.feature.auth.AuthorityRepository;
import com.istad.banking.feature.user.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInit {

    private final RoleRepository roleRepository;
    private final AuthorityRepository authorityRepository;
    @PostConstruct
    void init() {
        if (roleRepository.count() < 1) {

            Authorities userRead = new Authorities();
            userRead.setName("user:read");
            Authorities userWrite = new Authorities();
            userWrite.setName("user:write");

            Authorities transactionRead = new Authorities();
            transactionRead.setName("transaction:read");
            Authorities transactionWrite = new Authorities();
            transactionWrite.setName("transaction:write");

            Authorities accountRead = new Authorities();
            accountRead.setName("account:read");
            Authorities accountWrite = new Authorities();
            accountWrite.setName("account:write");

            Authorities accountTypeRead = new Authorities();
            accountTypeRead.setName("accountType:read");
            Authorities accountTypeWrite = new Authorities();
            accountTypeWrite.setName("accountType:write");

            Roles user = new Roles();
            user.setName("USER");
            user.setAuthorities(List.of(
                    userRead, transactionRead, accountRead, accountTypeRead
            ));
            Roles customer = new Roles();
            customer.setName("CUSTOMER");
            customer.setAuthorities(List.of(
                    userWrite, transactionWrite, accountWrite
            ));

            Roles staff = new Roles();
            staff.setName("STAFF");
            staff.setAuthorities(List.of(
                    accountTypeWrite
            ));
            Roles admin = new Roles();
            admin.setName("ADMIN");
            admin.setAuthorities(List.of(
                    userWrite, accountWrite, accountTypeWrite
            ));
            roleRepository.saveAll(
                    List.of(user, customer, staff, admin)
            );
        }

    }
}