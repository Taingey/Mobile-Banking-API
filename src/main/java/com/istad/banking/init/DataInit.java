package com.istad.banking.init;

import com.istad.banking.domain.Roles;
import com.istad.banking.feature.user.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInit {

    private final RoleRepository roleRepository;

    @PostConstruct
    void init() {

        if (roleRepository.count() < 1) {
            Roles user = new Roles();
            user.setName("USER");

            Roles customer = new Roles();
            customer.setName("CUSTOMER");

            Roles staff = new Roles();
            staff.setName("STAFF");

            Roles admin = new Roles();
            admin.setName("ADMIN");

            roleRepository.saveAll(
                    List.of(user, customer, staff, admin)
            );
        }

    }

}