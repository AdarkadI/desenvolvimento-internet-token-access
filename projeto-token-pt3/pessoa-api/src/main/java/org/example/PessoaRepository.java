package org.example;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PessoaRepository extends MongoRepository<Pessoa, String> {
}
