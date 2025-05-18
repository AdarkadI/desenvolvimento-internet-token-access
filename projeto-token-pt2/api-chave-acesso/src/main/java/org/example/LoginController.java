package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acesso")
public class LoginController {

    @Autowired
    private AcessoRepository acessoRepository;

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody LoginDTO loginDTO) {
        boolean valido = acessoRepository
                .findByUsuarioAndSenha(loginDTO.getUsuario(), loginDTO.getSenha())
                .isPresent();
        return ResponseEntity.ok(valido);
    }
}
