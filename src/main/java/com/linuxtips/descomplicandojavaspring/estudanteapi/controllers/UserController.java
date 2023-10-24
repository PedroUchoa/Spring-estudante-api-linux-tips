package com.linuxtips.descomplicandojavaspring.estudanteapi.controllers;

import com.linuxtips.descomplicandojavaspring.estudanteapi.exceptions.UserDuplicadoException;
import com.linuxtips.descomplicandojavaspring.estudanteapi.model.User;
import com.linuxtips.descomplicandojavaspring.estudanteapi.services.UserService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    Counter usuariosCadastradosCounter;

    public UserController(MeterRegistry registry){
        usuariosCadastradosCounter = Counter.builder("usuarios_cadastrados_counter")
                .description("Usuarios cadastrados")
                .register(registry);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<User> createUser(@RequestBody User data) throws UserDuplicadoException {
        usuariosCadastradosCounter.increment();
        userService.createUser(data);
        return ResponseEntity.status((HttpStatus.CREATED)).build();
    }




}
