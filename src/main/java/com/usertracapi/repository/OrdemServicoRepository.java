package com.usertracapi.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.usertracapi.constants.Status;
import com.usertracapi.model.OrdemServico;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long>{
    

    @Query("SELECT o FROM tb_ordemservicos o WHERE o.cliente.idCliente = :idCliente")
    List<OrdemServico> findByCliente(@Param("idCliente") Long idCliente);

    List<OrdemServico> findByStatus(Status status);

}
