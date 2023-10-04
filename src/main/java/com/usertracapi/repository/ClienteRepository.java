package com.usertracapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usertracapi.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    
    List<Cliente> findByNome(String nome);

    List<Cliente> findByNomeAndSobrenome(String nome, String sobrenome);

    List<Cliente> findByCpf(String cpf);
}
