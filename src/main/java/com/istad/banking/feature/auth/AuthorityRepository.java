package com.istad.banking.feature.auth;

import com.istad.banking.domain.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authorities, Integer> {
}
