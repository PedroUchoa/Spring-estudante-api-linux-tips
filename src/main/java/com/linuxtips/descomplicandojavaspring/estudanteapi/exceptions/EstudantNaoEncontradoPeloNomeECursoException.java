package com.linuxtips.descomplicandojavaspring.estudanteapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Estudante não encontrado pelo nome e/ou curso na base")
public class EstudantNaoEncontradoPeloNomeECursoException extends Exception{

    public EstudantNaoEncontradoPeloNomeECursoException(String name, String curso){
        super("Estudante com o nome: "+name+ " E Curso " + curso + " Não encontado na base");
    }


}
