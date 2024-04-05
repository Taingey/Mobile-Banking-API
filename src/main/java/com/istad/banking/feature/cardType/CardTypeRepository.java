package com.istad.banking.feature.cardType;

import com.istad.banking.domain.CardType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardTypeRepository extends JpaRepository<CardType, Long> {
    Optional<CardType> findByName(String name);
}
