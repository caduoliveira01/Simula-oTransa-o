package com.dev.simular_transacao.service;

import com.dev.simular_transacao.infrastucture.exceptions.UserNotFound;
import com.dev.simular_transacao.infrastucture.model.User;
import com.dev.simular_transacao.infrastucture.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public User findUser(Long id){
        return repository.findById(id).orElseThrow(()-> new UserNotFound("Usuário não enconjtrado"));
    }
}
