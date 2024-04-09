package com.istad.banking.feature.transaction;

import com.istad.banking.feature.transaction.dto.TransactionCreateRequest;
import com.istad.banking.feature.transaction.dto.TransactionResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    TransactionResponse transfer(@Valid @RequestBody TransactionCreateRequest transactionCreateRequest){
        return  transactionService.transfer(transactionCreateRequest);
    }

    @GetMapping
    public Page<TransactionResponse> getAllTransaction(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size){
        return transactionService.getTransactionHistorySortedByDate(page, size);

    }
}
