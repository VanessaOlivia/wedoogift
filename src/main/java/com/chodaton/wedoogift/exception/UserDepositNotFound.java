package com.chodaton.wedoogift.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserDepositNotFound extends RuntimeException {
    public UserDepositNotFound(Integer id){
        super(String.format("Le compte depot avec l'id %s n'existe pas' ! ", id));
    }
}
