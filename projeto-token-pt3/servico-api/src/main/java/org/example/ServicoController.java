package org.example;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    private final ServicoRepository repository;
    private final JwtUtil jwtUtil;

    public ServicoController(ServicoRepository repository, JwtUtil jwtUtil) {
        this.repository = repository;
        this.jwtUtil = jwtUtil;
    }

    private boolean isAuthorized(String token) {
        return token != null && token.startsWith("Bearer ") && jwtUtil.validateToken(token.substring(7));
    }

    @PostMapping
    public ResponseEntity<?> registrarServico(@RequestHeader("Authorization") String authHeader,
                                              @RequestBody ServicoDTO dto) {
        if (!isAuthorized(authHeader)) return ResponseEntity.status(401).build();

        Servico servico = Servico.builder()
                .tipo(dto.getTipo())
                .data(LocalDate.parse(dto.getData()))
                .valor(dto.getValor())
                .status(dto.getStatus())
                .animalId(dto.getAnimalId())
                .build();

        return ResponseEntity.ok(repository.save(servico));
    }

    @GetMapping
    public ResponseEntity<?> listarTodos(@RequestHeader("Authorization") String authHeader) {
        if (!isAuthorized(authHeader)) return ResponseEntity.status(401).build();

        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/animal/{animalId}")
    public ResponseEntity<?> listarPorAnimal(@RequestHeader("Authorization") String authHeader,
                                             @PathVariable String animalId) {
        if (!isAuthorized(authHeader)) return ResponseEntity.status(401).build();

        return ResponseEntity.ok(repository.findByAnimalId(animalId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@RequestHeader("Authorization") String authHeader,
                                         @PathVariable String id) {
        if (!isAuthorized(authHeader)) return ResponseEntity.status(401).build();

        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
