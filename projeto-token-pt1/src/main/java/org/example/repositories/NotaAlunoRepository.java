package org.example.repositories;

import org.example.model.NotaAluno;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotaAlunoRepository extends MongoRepository<NotaAluno, Integer>{
}
