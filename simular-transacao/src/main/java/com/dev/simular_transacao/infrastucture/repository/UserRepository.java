package com.dev.simular_transacao.infrastucture.repository;

import com.dev.simular_transacao.infrastucture.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Long> {
}
