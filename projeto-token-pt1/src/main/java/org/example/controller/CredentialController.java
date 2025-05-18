package org.example.controller;

import org.example.constant.Constant;
import org.example.model.ResponseToken;
import org.example.model.Usuario;
import org.example.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class CredentialController {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Value("${credential.email}")
    private String email;

    @Value("${credential.senha}")
    private String senha;

    @PostMapping(Constant.API_LOGIN)
    public ResponseToken getToken(@RequestBody Usuario user){
        if (user.getEmail().equals(email) && user.getSenha().equals(senha)){
            String token = jwtTokenProvider.generateToken(user.getEmail());
            return new ResponseToken("Authenticated", token);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid User");
    }
}
