package org.example;

import lombok.Data;

@Data
public class ServicoDTO {
    private String tipo;
    private String data; // formato ISO 8601
    private double valor;
    private String status;
    private String animalId;
}
