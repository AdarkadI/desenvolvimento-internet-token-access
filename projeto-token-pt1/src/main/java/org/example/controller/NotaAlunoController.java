package org.example.controller;

import org.example.constant.Constant;
import org.example.model.NotaAluno;
import org.example.repositories.NotaAlunoRepository;
import org.example.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class NotaAlunoController {
    @Autowired
    private JwtTokenProvider jwtTokenValidator;

    @Autowired
    private NotaAlunoRepository notaAlunoRepository;

    @PostMapping(Constant.API_NOTAALUNO_INSERIR)
    public ResponseEntity<NotaAluno> criar (@RequestHeader(value = "Authorization") String token, @RequestBody NotaAluno notaAluno) {
        if (jwtTokenValidator.validateToken(token)) {
            return ResponseEntity.ok(notaAlunoRepository.save(notaAluno));
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthenticated User");
        }
    }

    @GetMapping(Constant.API_NOTAALUNO_LISTAR)
    public List<NotaAluno> findAll (@RequestHeader(value = "Authorization") String token){
        if (jwtTokenValidator.validateToken(token)) {
            return notaAlunoRepository.findAll();
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthenticated User");
        }
    }
}
