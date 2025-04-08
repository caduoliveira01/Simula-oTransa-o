package com.dev.simular_transacao.controller;

import com.dev.simular_transacao.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transfer")
public class TransactionControler {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Void> makeTransfer(@RequestBody TransactionDTO transactionDTO){
        transactionService.transferValue(transactionDTO);
        return ResponseEntity.accepted().build();
    }
}
