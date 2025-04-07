package com.dev.simular_transacao.infrastucture.repository;

import com.dev.simular_transacao.infrastucture.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet,Long> {
}
