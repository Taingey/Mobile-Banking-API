package com.istad.banking.feature.card;

import com.istad.banking.domain.Card;
import com.istad.banking.feature.card.dto.CardCreateRequest;
import com.istad.banking.feature.card.dto.CardResponse;
import com.istad.banking.feature.cardType.CardTypeService;
import com.istad.banking.mapper.CardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class CardServiceImpl implements CardService{
    private final CardRepository cardRepository;
    private final CardTypeService cardTypeService;
    private final CardMapper cardMapper;
    @Override
    public void createCard(CardCreateRequest cardCreateRequest) {
        Card card = new Card();
        card.setCardNumber(cardCreateRequest.cardNumber());
        card.setCvv(cardCreateRequest.cvv());
        card.setHolderName(cardCreateRequest.holderName());
        card.setIssuedAt(cardCreateRequest.issuedAt());
        card.setIsDeleted(cardCreateRequest.isDeleted());

        card.setCardType(cardTypeService.findByCardTypName(cardCreateRequest.cardTypeName()));
         cardRepository.save(card);
    }

    @Override
    public List<CardResponse> findAllCard() {
        List<Card> cards = cardRepository.findAll();
        return cards.stream()
                .map(cardMapper::toFindAllCard)
                .collect(Collectors.toList());
    }
}