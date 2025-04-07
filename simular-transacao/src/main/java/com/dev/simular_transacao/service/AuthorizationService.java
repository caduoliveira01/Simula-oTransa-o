package com.dev.simular_transacao.service;

import com.dev.simular_transacao.infrastucture.clients.AuthorizationClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthorizationService {

    private final AuthorizationClient authorizationClient;

    public boolean validateTransfer(){
        if (Objects.equals(authorizationClient.validateAuthorization().dataDTO().authorization(),"true")){
            return true;
        }
        return false;
    }
}
