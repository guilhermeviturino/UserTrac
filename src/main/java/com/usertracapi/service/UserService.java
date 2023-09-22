package com.usertracapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.usertracapi.dto.UsuarioDTO;
import com.usertracapi.model.Usuario;
import com.usertracapi.repository.UsuarioRepository;

@Service
public class UserService  {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario registerUser(UsuarioDTO usuario){
        String encryptedPassword = new BCryptPasswordEncoder().encode(usuario.getSenha());
        Usuario user = new Usuario(usuario.getLogin(), encryptedPassword, usuario.getStatus());
        this.usuarioRepository.save(user);
        return user;
    }
}
