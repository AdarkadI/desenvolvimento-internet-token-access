package org.example.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
public class NotaAluno {
    private  int id;
    private String nome;
    private float notaUm;
    private float notaDois;
    private float media;
    private String email;
}
