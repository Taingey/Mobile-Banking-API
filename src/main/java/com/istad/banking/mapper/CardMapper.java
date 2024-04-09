package com.istad.banking.mapper;

import com.istad.banking.domain.Card;
import com.istad.banking.domain.CardType;
import com.istad.banking.feature.card.dto.CardResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CardMapper {

    @Mapping(target = "cardNumber", source = "card.cardType.name")
    CardResponse toFindAllCard(Card card);
}
