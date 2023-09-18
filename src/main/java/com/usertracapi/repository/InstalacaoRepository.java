package com.usertracapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.usertracapi.constants.Status;
import com.usertracapi.model.Instalacao;

public interface InstalacaoRepository extends JpaRepository<Instalacao, Long>{
    
    @Query("SELECT i FROM tb_instalacoes i WHERE i.cliente.idCliente = :idCliente")
    List<Instalacao> findByCliente (@Param("idCliente") Long idCliente);

    List<Instalacao> findByStatus(Status status);
}
