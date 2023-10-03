package com.linuxtips.descomplicandojavaspring.estudanteapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Estudante não encontrado pelo nome na base")
public class EstudantNaoEncontradoPeloNomeException extends Exception{

    public EstudantNaoEncontradoPeloNomeException(String name){
        super("Estudandte com o nome: "+name+" Não encontado na base");
    }


}
