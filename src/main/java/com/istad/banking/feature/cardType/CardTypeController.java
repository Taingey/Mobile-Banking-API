package com.istad.banking.feature.cardType;

import com.istad.banking.feature.cardType.dto.CardTypeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/cardType")
public class CardTypeController {
    private final CardTypeService cardTypeService;

    @GetMapping
    public List<CardTypeResponse> findCardAll(){
        return cardTypeService.findAll();
    }
    @GetMapping("/{name}")
    public CardTypeResponse findByName(@PathVariable String name){
        return cardTypeService.findByName(name);
    }


}
