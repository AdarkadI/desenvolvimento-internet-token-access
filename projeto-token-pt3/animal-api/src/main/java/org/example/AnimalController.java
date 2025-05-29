package org.example;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animais")
public class AnimalController {

    private final AnimalRepository repository;
    private final JwtUtil jwtUtil;

    public AnimalController(AnimalRepository repository, JwtUtil jwtUtil) {
        this.repository = repository;
        this.jwtUtil = jwtUtil;
    }

    private boolean isAuthorized(String token) {
        return token != null && token.startsWith("Bearer ") && jwtUtil.validateToken(token.substring(7));
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestHeader("Authorization") String authHeader,
                                       @RequestBody AnimalDTO dto) {
        if (!isAuthorized(authHeader)) return ResponseEntity.status(401).build();

        Animal animal = Animal.builder()
                .nome(dto.getNome())
                .especie(dto.getEspecie())
                .raca(dto.getRaca())
                .idade(dto.getIdade())
                .pessoaId(dto.getPessoaId())
                .build();

        return ResponseEntity.ok(repository.save(animal));
    }

    @GetMapping
    public ResponseEntity<?> listarTodos(@RequestHeader("Authorization") String authHeader) {
        if (!isAuthorized(authHeader)) return ResponseEntity.status(401).build();

        List<Animal> animais = repository.findAll();
        return ResponseEntity.ok(animais);
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