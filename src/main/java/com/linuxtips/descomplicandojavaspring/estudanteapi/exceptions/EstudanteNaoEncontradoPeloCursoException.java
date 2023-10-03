package com.linuxtips.descomplicandojavaspring.estudanteapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Tipo de curso inexistente")
public class EstudanteNaoEncontradoPeloCursoException extends Exception{
    public EstudanteNaoEncontradoPeloCursoException(String curso){
        super("Não existe nenhum curso com esse nome:" + curso);
    }

}
