package org.example.controller;

import org.example.constant.Constant;
import org.example.model.Usuario;
import org.example.repositories.UsuarioRepository;
import org.example.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private JwtTokenProvider jwtTokenValidator;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping(Constant.API_USARIO_INSERIR)
    public ResponseEntity<Usuario> criar (@RequestHeader(value = "Authorization") String token, @RequestBody Usuario usuario) {
        if (jwtTokenValidator.validateToken(token)) {
            return ResponseEntity.ok(usuarioRepository.save(usuario));
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthenticated User");
        }
    }

    @GetMapping(Constant.API_USARIO_LISTAR)
    public List<Usuario> findAll (@RequestHeader(value = "Authorization") String token){
        if (jwtTokenValidator.validateToken(token)) {
                return usuarioRepository.findAll();
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthenticated User");
        }
    }

}
