package com.linuxtips.descomplicandojavaspring.estudanteapi.services;

import com.linuxtips.descomplicandojavaspring.estudanteapi.exceptions.UserDuplicadoException;
import com.linuxtips.descomplicandojavaspring.estudanteapi.model.User;
import com.linuxtips.descomplicandojavaspring.estudanteapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createUser(User user) throws UserDuplicadoException {
        if(!userRepository.findByLogin(user.getLogin()).isEmpty()){
            throw new UserDuplicadoException(user.getLogin());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

    }

}
