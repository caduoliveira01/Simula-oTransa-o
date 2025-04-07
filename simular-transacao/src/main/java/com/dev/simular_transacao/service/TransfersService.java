package com.dev.simular_transacao.service;

import com.dev.simular_transacao.controller.TransationDTO;
import com.dev.simular_transacao.infrastucture.model.User;
import com.dev.simular_transacao.infrastucture.model.UserType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransfersService {
    private final UserService userService;
    private final AuthorizationService authorizationService;

    @Transactional
    public void transferValue(TransationDTO transationDTO){
        User payer = userService.findUser(transationDTO.payer());
        User receiver = userService.findUser(transationDTO.payee());

        validateUser(payer);
        validateBalance(payer, transationDTO.value());
        validateTransfer();

        payer.getWallet().setBalance(payer.getWallet().getBalance().subtract(transationDTO.value()));

    }

    private void validateUser(User user){
        try{
            if (user.getUserType().equals(UserType.VENDEDOR)){
                throw new IllegalArgumentException("Transação não autorizada para esse tipo de usuário");
            }
        }catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private void validateBalance(User user, BigDecimal value) {
        try {
            if (user.getWallet().getBalance().compareTo(value) < 0) {
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
}
