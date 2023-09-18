package com.usertracapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Endereco {

    @Column(nullable = true, length = 10)
    @Pattern(regexp = "\\d{2}.\\d{3}-\\d{3}")
    private String cep;

    @Column(nullable = true, length = 255)
    private String logradouro;

    @Column(nullable = true, length = 10)
    private String numero;

    @Column(nullable = true, length = 50)
    private String complemento;

    @Column(nullable = true, length = 50)
    private String bairro;

    @Column(nullable = true, length = 50)
    private String cidade;

    @Column(nullable = true, length = 2)
    private String uf;

    @Column(nullable = true, length = 255)
    private String pontoDeReferencia;
}
