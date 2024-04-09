package com.istad.banking.feature.card;

import com.istad.banking.feature.card.dto.CardCreateRequest;
import com.istad.banking.feature.card.dto.CardResponse;

import java.util.List;

public interface CardService {
    void createCard(CardCreateRequest cardRequest);

    List<CardResponse> findAllCard();
}
