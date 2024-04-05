package com.istad.banking.feature.cardType;

import com.istad.banking.domain.CardType;
import com.istad.banking.feature.cardType.dto.CardTypeResponse;

import java.util.List;

public interface CardTypeService {
    List<CardTypeResponse> findAll();
    CardTypeResponse findByName(String name);
}
