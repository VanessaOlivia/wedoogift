package com.chodaton.wedoogift.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(Double actualBalance){
        super(String.format("Solde insuffisant pour effectuer une donation! votre solde actuel est %.2f ", actualBalance));
    }
}
