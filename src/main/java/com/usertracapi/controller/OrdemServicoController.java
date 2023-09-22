package com.usertracapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usertracapi.constants.Status;
import com.usertracapi.model.OrdemServico;
import com.usertracapi.repository.OrdemServicoRepository;

@Secured({ "ROLE_ADMIN", "ROLE_USUARIO" })
@RestController
@RequestMapping(value = "/ordemservicos")
public class OrdemServicoController {

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @GetMapping
    public ResponseEntity<Page<OrdemServico>> listarOrdemServico(Pageable paginacao) {
        return ResponseEntity.status(HttpStatus.OK).body(ordemServicoRepository.findAll(paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdemServico> buscarOrdemServicoPeloId(@PathVariable("id") Long id) {
        Optional<OrdemServico> ordemExistente = ordemServicoRepository.findById(id);

        if (ordemExistente.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(ordemExistente.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<OrdemServico>> bucerPorStatus(@PathVariable("status") Status status) {
        return ResponseEntity.status(HttpStatus.OK).body(ordemServicoRepository.findByStatus(status));
    }

    @GetMapping("/cliente/{idCliente}")
    public List<OrdemServico> obterOrdemServicoDoCliente(@PathVariable("idCliente") Long idCliente) {
        return ordemServicoRepository.findByCliente(idCliente);
    }

    @PostMapping
    public ResponseEntity<OrdemServico> cadastrarCliente(@RequestBody OrdemServico ordemServico) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ordemServicoRepository.save(ordemServico));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdemServico> atualizarClientePeloId(@PathVariable("id") long id,
            @RequestBody OrdemServico ordemServico) {
        Optional<OrdemServico> ordemExistente = ordemServicoRepository.findById(id);

        if (ordemExistente.isPresent()) {
            ordemExistente.get().setCliente(ordemServico.getCliente());
            ordemExistente.get().setDataAbertura(ordemServico.getDataAbertura());
            ordemExistente.get().setDescricaoProblema(ordemServico.getDescricaoProblema());
            ordemExistente.get().setDescricaoSolucao(ordemServico.getDescricaoSolucao());
            ordemExistente.get().setStatus(ordemServico.getStatus());

            return ResponseEntity.status(HttpStatus.OK).body(ordemServicoRepository.save(ordemExistente.get()));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarcOrdemServicoPeloId(@PathVariable("id") Long id) {
        Optional<OrdemServico> ordemExistente = ordemServicoRepository.findById(id);
        if (ordemExistente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        ordemServicoRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Ordem de serviço deletada com sucesso!");
    }

}
