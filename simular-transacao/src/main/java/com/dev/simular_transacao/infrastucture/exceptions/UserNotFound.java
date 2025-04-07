package com.dev.simular_transacao.infrastucture.exceptions;

public class UserNotFound extends RuntimeException{

    public UserNotFound(String message){
        super(message);
    }
}
