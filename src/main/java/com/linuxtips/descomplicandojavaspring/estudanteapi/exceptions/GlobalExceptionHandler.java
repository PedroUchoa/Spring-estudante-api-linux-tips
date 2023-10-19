package com.linuxtips.descomplicandojavaspring.estudanteapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EstudanteNaoEncontradoException.class)
    public ResponseEntity<String> handleEstudanteNaoEncontradoException(Exception ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage() + "\n" + ex.getClass());
    }

    @ExceptionHandler(EstudanteDuplicadoException.class)
    public ResponseEntity<String> hadleEstudanteDuplicadoException(Exception ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage() + "\n" + ex.getClass());
    }

    @ExceptionHandler(UserDuplicadoException.class)
    public ResponseEntity<String> handleUserDuplicadoException(Exception ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage() + "\n" + ex.getClass());
    }

    @ExceptionHandler(EstudantNaoEncontradoPeloNomeException.class)
    public ResponseEntity<String> handleEstudantNaoEncontradoPeloNomeException(Exception ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage() + "\n" + ex.getClass() );
    }

    @ExceptionHandler(EstudanteNaoEncontradoPeloCursoException.class)
    public ResponseEntity<String> handleEstudanteNaoEncontradoPeloCursoException(Exception ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage() + "\n" + ex.getClass());
    }

    @ExceptionHandler(EstudantNaoEncontradoPeloNomeECursoException.class)
    public ResponseEntity<String> handleEstudantNaoEncontradoPeloNomeECursoException(Exception ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage() + "\n" + ex.getClass());
    }

    @ExceptionHandler(EstudanteNaoEncontradoPeloEnderecoException.class)
    public ResponseEntity<String> handleEstudanteNaoEncontradoPeloEnderecoException(Exception ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage() + "\n" + ex.getClass());
    }


}
