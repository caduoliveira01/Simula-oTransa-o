package com.dev.simular_transacao.service;

import com.dev.simular_transacao.controller.TransactionDTO;
import com.dev.simular_transacao.infrastucture.exceptions.BadRequestException;
import com.dev.simular_transacao.infrastucture.model.Transactions;
import com.dev.simular_transacao.infrastucture.model.Users;
import com.dev.simular_transacao.infrastucture.model.UserType;
import com.dev.simular_transacao.infrastucture.model.Wallet;
import com.dev.simular_transacao.infrastucture.repository.TransactionsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final UserService userService;
    private final AuthorizationService authorizationService;
    private final WalletService walletService;
    private final TransactionsRepository transactionsRepository;
    private final NotificationService notificationService;

    @Transactional
    public void transferValue(TransactionDTO transactionDTO){
        Users payer = userService.findUser(transactionDTO.payer());
        Users receiver = userService.findUser(transactionDTO.payee());

        validateUser(payer);
        validateBalance(payer, transactionDTO.value());
        validateTransfer();

        payer.getWallet().setBalance(payer.getWallet().getBalance().subtract(transactionDTO.value()));
        updateWallet(payer.getWallet());

        receiver.getWallet().setBalance(receiver.getWallet().getBalance().add(transactionDTO.value()));
        updateWallet(receiver.getWallet());

        Transactions transactions = Transactions.builder().
                value(transactionDTO.value())
                .payer(payer)
                .receiver(receiver)
                .build();

        transactionsRepository.save(transactions);
        sendNotification();
    }

    private void validateUser(Users users){
        try{
            if (users.getUserType().equals(UserType.VENDEDOR)){
                throw new IllegalArgumentException("Transação não autorizada para esse tipo de usuário");
            }
        }catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private void validateBalance(Users users, BigDecimal value) {
        try {
            if (users.getWallet().getBalance().compareTo(value) < 0) {
                throw new IllegalArgumentException("Transação não autorizada, saldo insuficiente");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private void validateTransfer() {
        try {
            if (!authorizationService.validateTransfer()) {
                throw new IllegalArgumentException("Transação não autorizada pela API");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private void updateWallet(Wallet wallet){
        walletService.save(wallet);
    }

    private void sendNotification(){
        try {
            notificationService.sendNotification();
        }catch (HttpClientErrorException e){
            throw new BadRequestException("Erro ao enviar notificação");
        }
    }
}
