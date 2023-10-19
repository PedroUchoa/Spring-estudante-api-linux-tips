package com.linuxtips.descomplicandojavaspring.estudanteapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "User duplicado - já existe um user com esse nome")
public class UserDuplicadoException extends Exception{
   public UserDuplicadoException(){
        super();
    }

    public UserDuplicadoException(String message){
       super(message);
    }

}
