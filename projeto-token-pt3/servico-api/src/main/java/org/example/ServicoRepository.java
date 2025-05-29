package org.example;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ServicoRepository extends MongoRepository<Servico, String> {
    List<Servico> findByAnimalId(String animalId);
}
