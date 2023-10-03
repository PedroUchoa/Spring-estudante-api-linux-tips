package com.linuxtips.descomplicandojavaspring.estudanteapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Nenhum estudante encontrado com esse endereço")
public class EstudanteNaoEncontradoPeloEnderecoException extends Exception{

    public EstudanteNaoEncontradoPeloEnderecoException(String endereco){
        super("Nenhum estudante encontrado no endereço: " + endereco);
    }

}
