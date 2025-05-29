package org.example.controllers;

import org.example.dto.AuthRequestDTO;
import org.example.dto.AuthResponseDTO;
import org.example.security.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Value("${credential.email}")
    private String validEmail;

    @Value("${credential.senha}")
    private String validSenha;

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO request) {
        if (validEmail.equals(request.getEmail()) && validSenha.equals(request.getSenha())) {
            String token = jwtUtil.generateToken(request.getEmail());
            return ResponseEntity.ok(new AuthResponseDTO(token));
        }
        return ResponseEntity.status(401).body("Credenciais inválidas");
    }
}
