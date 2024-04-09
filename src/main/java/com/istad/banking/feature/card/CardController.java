package com.istad.banking.feature.card;


import com.istad.banking.feature.card.dto.CardCreateRequest;
import com.istad.banking.feature.card.dto.CardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/card")
public class CardController {
    private final CardService cardService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCard(@RequestBody CardCreateRequest cardCreateRequest){
        cardService.createCard(cardCreateRequest);
    }

    @GetMapping
    public List<CardResponse> findAllCard(){
        return cardService.findAllCard();
    }

}
