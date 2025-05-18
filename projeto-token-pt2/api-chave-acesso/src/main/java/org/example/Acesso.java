package org.example;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "acessos")
@Data
public class Acesso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String usuario;
    private String senha;
}
