package com.usertracapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usertracapi.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByLogin(String login);

}
