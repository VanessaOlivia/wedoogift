package com.chodaton.wedoogift.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class CompanyNotFound extends RuntimeException {
    public CompanyNotFound(Integer id){
        super(String.format("Aucune compagnie avec l'id %s n'a été trouvée ! ", id));
    }
}
