package com.istad.banking.feature.cardType;

import com.istad.banking.domain.CardType;
import com.istad.banking.feature.cardType.dto.CardTypeResponse;
import com.istad.banking.mapper.CardTypeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CardTypeServiceImpl implements CardTypeService{
    private final CardTypeRepository cardTypeRepository;
    private final CardTypeMapper cardTypeMapper;
    @Override
    public List<CardTypeResponse> findAll() {
        List<CardType> cardTypes = cardTypeRepository.findAll();
        return cardTypes.stream()
                .map(cardTypeMapper::toCardTypeResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CardTypeResponse findByName(String name) {
        CardType cardType = cardTypeRepository.findByName(name)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Card type has not been found!"
                ));
        return cardTypeMapper.toCardTypeResponse(cardType);
    }
}
