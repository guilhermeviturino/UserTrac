package com.usertracapi.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.usertracapi.constants.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_ordemservicos")
public class OrdemServico {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrdemServico;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    @Column(nullable = true)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAbertura;

    @Column(columnDefinition = "TEXT")
    private String descricaoProblema;

    @Column(columnDefinition = "TEXT")
    private String descricaoSolucao;

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private Status status;
}
