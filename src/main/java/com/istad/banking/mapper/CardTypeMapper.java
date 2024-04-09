package com.istad.banking.mapper;

import com.istad.banking.domain.CardType;
import com.istad.banking.feature.cardType.dto.CardTypeResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CardTypeMapper {
    @Mapping(source = "name", target = "name")
    @Mapping(source = "isDeleted", target = "isDeleted")
    CardTypeResponse toCardTypeResponse(CardType cardType);
}