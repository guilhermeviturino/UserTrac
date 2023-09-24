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
        String encryptedPassword = new BCryptPasswordEncoder().encode(usuario.senha());
        Usuario user = new Usuario(usuario.login(), encryptedPassword, usuario.status());
        this.usuarioRepository.save(user);
        return user;
    }
}
