package com.istad.banking.feature.transaction;

import com.istad.banking.domain.Account;
import com.istad.banking.domain.Transactions;
import com.istad.banking.feature.account.AccountRepository;
import com.istad.banking.feature.transaction.dto.TransactionCreateRequest;
import com.istad.banking.feature.transaction.dto.TransactionPaymentRequest;
import com.istad.banking.feature.transaction.dto.TransactionResponse;
import com.istad.banking.mapper.TransactionMapper;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService{
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final TransactionMapper transactionMapper;
    @Transactional
    @Override
    public TransactionResponse transfer(TransactionCreateRequest transactionCreateRequest) {

        log.info("transfer(TransactionCreateRequest transactionCreateRequest) : {}", transactionCreateRequest);

        Account owner = accountRepository.findByAccountNo(transactionCreateRequest.ownerAccountNo())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Account owner has not been fount"
                        ));

        Account transferReceiver = accountRepository.findByAccountNo(transactionCreateRequest.transferReceiveAccountNo())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Account transfer has not been fount"
                ));

        if(owner.getBalance().doubleValue() < transactionCreateRequest.amount().doubleValue()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Insufficient balance"
                    );
        }
        if(transactionCreateRequest.amount().doubleValue() >= owner.getBalance().doubleValue()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Transaction has been over the transfer limit"
                    );
        }

        owner.setBalance(owner.getBalance().subtract(transactionCreateRequest.amount()));

        transferReceiver.setBalance(owner.getBalance().add(transactionCreateRequest.amount()));

        Transactions transactions = transactionMapper.fromTransactionsCreateRequest(transactionCreateRequest);

        transactions.setOwner(owner);
        transactions.setTransferReceiver(transferReceiver);
        transactions.setTransactionType("TRANSFER");
        transactions.setTransactionAt(LocalDateTime.now());
        transactions.setStatus(true);

        transactions = transactionRepository.save(transactions);
        return transactionMapper.toTransactionResponse(transactions);
    }

    @Override
    public Page<TransactionResponse> getTransactionHistorySortedByDate(int page, int size) {
        if(page < 0 || size < 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Page number and size must be greater than 0"
                    );
        }
        Sort orders = Sort.by(Sort.Direction.DESC,"transactionAt");
        PageRequest pageRequest = PageRequest.of(page, size, orders);
        Page<Transactions> transactions = transactionRepository.findAll(pageRequest);

        return transactions.map(transactionMapper::toTransactionResponse);
    }

    @Override
    public void payment(@Valid TransactionPaymentRequest transactionPaymentRequest) {
        Account owner = accountRepository.findByAccountNo(transactionPaymentRequest.ownerAccountNo())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Account owner has not been found"
                        ));

        BigDecimal amount = transactionPaymentRequest.amount();

        if(owner.getBalance().compareTo(amount) < 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Insufficient balance"
                    );
        }

        owner.setBalance(owner.getBalance().subtract(amount));

        Transactions transactions = new Transactions();
        transactions.setOwner(owner);
        transactions.setAmount(amount);
        transactions.setRemark("Payment to : " + transactionPaymentRequest.paymentReceiver());
        transactions.setTransactionType("PAYMENT");
        transactions.setTransactionAt(LocalDateTime.now());
        transactions.setStatus(true);

        transactionRepository.save(transactions);
    }


}
