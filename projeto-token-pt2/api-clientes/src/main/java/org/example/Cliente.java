package org.example;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "clientes")
@Data
public class Cliente {
    @Id
    private String id;
    private String nome;
    private String telefone;
    private LocalDateTime dataCadastro;
}
