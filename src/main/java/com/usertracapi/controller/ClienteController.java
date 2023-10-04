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

import com.usertracapi.model.Cliente;
import com.usertracapi.repository.ClienteRepository;

@Secured("ROLE_ADMIN")
@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    @Secured({ "ROLE_ADMIN", "ROLE_USER" })
    public ResponseEntity<Page<Cliente>> listarClientes(Pageable paginacao) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.findAll(paginacao));
    }

    @GetMapping("/{id}")
    @Secured({ "ROLE_ADMIN", "ROLE_USER" })
    public ResponseEntity<Cliente> buscarClientePeloId(@PathVariable("id") Long id) {
        Optional<Cliente> clienteExistente = clienteRepository.findById(id);

        if (clienteExistente.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(clienteExistente.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/nome/{nome}")
    @Secured({ "ROLE_ADMIN", "ROLE_USER" })
    public ResponseEntity<List<Cliente>> buscarClientePorNome(@PathVariable("nome") String nome) {
        

        if (nome.equalsIgnoreCase(nome)) {
            return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.findByNome(nome));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    @GetMapping("/cpf/{cpf}")
    @Secured({ "ROLE_ADMIN", "ROLE_USER" })
    public ResponseEntity<List<Cliente>> buscarClientePeloCpf(@PathVariable("cpf") String cpf) {

        if (cpf.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.findByCpf(cpf));
    }

    @PostMapping
    @Secured({ "ROLE_ADMIN", "ROLE_USER" })
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteRepository.save(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarClientePeloId(@PathVariable("id") long id, @RequestBody Cliente cliente) {
        Optional<Cliente> clienteExistente = clienteRepository.findById(id);

        if (clienteExistente.isPresent()) {
            clienteExistente.get().setNome(cliente.getNome());
            clienteExistente.get().setSobrenome(cliente.getSobrenome());
            clienteExistente.get().setCpf(cliente.getCpf());
            clienteExistente.get().setTelefone(cliente.getTelefone());
            clienteExistente.get().setEndereco(cliente.getEndereco());

            return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.save(clienteExistente.get()));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarcClientePeloId(@PathVariable("id") Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);

        if (cliente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        clienteRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Cliente deletado com sucesso!");
    }

}
