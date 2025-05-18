package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AuthService authService;

    @PostMapping("/adicionar")
    public Object adicionar(@RequestHeader("usuario") String usuario,
                            @RequestHeader("senha") String senha,
                            @RequestBody Cliente cliente) {
        if (!authService.autenticar(usuario, senha)) {
            return "Acesso negado: login inválido.";
        }
        cliente.setDataCadastro(LocalDateTime.now());
        return clienteRepository.save(cliente);
    }

    @GetMapping("/listar")
    public Object listar(@RequestHeader("usuario") String usuario,
                         @RequestHeader("senha") String senha) {
        if (!authService.autenticar(usuario, senha)) {
            return "Acesso negado: login inválido.";
        }
        return clienteRepository.findAll();
    }
}