package com.dev.simular_transacao.infrastucture.repository;

import com.dev.simular_transacao.infrastucture.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionsRepository extends JpaRepository<Transactions,Long> {
}
