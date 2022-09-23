package com.chodaton.wedoogift.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CompanyAccountNotFound extends RuntimeException {
    public CompanyAccountNotFound(Integer id){
        super(String.format("La compagnie avec l'id %s ne possède aucun compte ! ", id));
    }
}
