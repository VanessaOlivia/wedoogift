package com.chodaton.wedoogift.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class UserNotFound extends RuntimeException {
    public UserNotFound(Integer id){
        super(String.format("Aucune utilisateur avec l'id %s n'a été trouvé ! ", id));
    }
}
