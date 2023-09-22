package com.usertracapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.usertracapi.repository.UsuarioRepository;

@Service
public class AuthenticationService implements UserDetailsService{
    

     @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        return usuarioRepository.findByLogin(username);
    }

    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private UsuarioRepository usuarioRepository;

}