package com.dev.simular_transacao.service;

import com.dev.simular_transacao.infrastucture.exceptions.UserNotFound;
import com.dev.simular_transacao.infrastucture.model.Wallet;
import com.dev.simular_transacao.infrastucture.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository repository;

    public void save(Wallet wallet){
        repository.save(wallet);
    }
}
