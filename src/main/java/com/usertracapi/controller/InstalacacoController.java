package com.usertracapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usertracapi.constants.Status;
import com.usertracapi.model.Instalacao;
import com.usertracapi.repository.InstalacaoRepository;

@RestController
@RequestMapping(value = "/instalacoes")
public class InstalacacoController {

    @Autowired
    private InstalacaoRepository instalacaoRepository;

    @GetMapping
    public ResponseEntity<Page<Instalacao>> Instalacoes(Pageable paginacao) {
        return ResponseEntity.status(HttpStatus.OK).body(instalacaoRepository.findAll(paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instalacao> buscarInstalacaoPeloId(@PathVariable("id") Long id) {
        Optional<Instalacao> instalacaoExistente = instalacaoRepository.findById(id);

        if (instalacaoExistente.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(instalacaoExistente.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/cliente/{idCliente}")
    public List<Instalacao> obterInstalacaoDoCliente(@PathVariable("idCliente") Long idCliente) {
        return instalacaoRepository.findByCliente(idCliente);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Instalacao>> bucerPorStatus(@PathVariable("status") Status status) {
        return ResponseEntity.status(HttpStatus.OK).body(instalacaoRepository.findByStatus(status));
    }
    @PostMapping
    public ResponseEntity<Instalacao> cadastrarCliente(@RequestBody Instalacao instalacao) {
            return ResponseEntity.status(HttpStatus.CREATED).body(instalacaoRepository.save(instalacao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Instalacao> atualizarClientePeloId(@PathVariable("id") long id, @RequestBody Instalacao instalacao) {
        Optional<Instalacao> instalacaoExistente = instalacaoRepository.findById(id);
        
        if (instalacaoExistente.isPresent()) {
            instalacaoExistente.get().setCliente(instalacao.getCliente());
            instalacaoExistente.get().setDataAgendada(instalacao.getDataAgendada());
            instalacaoExistente.get().setStatus(instalacao.getStatus());

            return ResponseEntity.status(HttpStatus.OK).body(instalacaoRepository.save(instalacaoExistente.get()));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarcClientePeloId(@PathVariable Long id) {
        Optional<Instalacao> instalacaoExistente = instalacaoRepository.findById(id);

        if (instalacaoExistente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        instalacaoRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Instalação deletada com sucesso!");
    }
}
