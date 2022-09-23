package com.chodaton.wedoogift.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnknownDepositType extends RuntimeException {
    public UnknownDepositType (){
        super(String.format("Le type de depot est inconnu ! Valeurs autorisées  : meal ou gift "));
    }
}
