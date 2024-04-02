package com.istad.banking.feature.repository;

import com.istad.banking.domain.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Roles, Long> {
    Optional<Roles> findByName(String name);
}
