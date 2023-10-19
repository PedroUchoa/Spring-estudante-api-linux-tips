package com.linuxtips.descomplicandojavaspring.estudanteapi.controllers;

import com.linuxtips.descomplicandojavaspring.estudanteapi.dtos.AuthenticationData;
import com.linuxtips.descomplicandojavaspring.estudanteapi.dtos.DadosTokenJWT;
import com.linuxtips.descomplicandojavaspring.estudanteapi.model.User;
import com.linuxtips.descomplicandojavaspring.estudanteapi.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity makeLogin(@RequestBody AuthenticationData dados){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(),dados.password());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        String tokenJwt = tokenService.tokenGenerator((User) authentication.getPrincipal());
        return ResponseEntity.ok(new DadosTokenJWT(tokenJwt));

    }

}
