package com.dev.simular_transacao.service;

import com.dev.simular_transacao.infrastucture.clients.AuthorizationClient;
import com.dev.simular_transacao.infrastucture.clients.NotificationClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationClient notificationClient;

    public void sendNotification(){
        notificationClient.sendNotification();
    }
}
