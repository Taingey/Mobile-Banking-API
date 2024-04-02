package com.istad.banking.feature.services;

import com.istad.banking.feature.dto.CardCreateRequest;

public interface CardService {
    void addCard(String userId, CardCreateRequest request);
}
