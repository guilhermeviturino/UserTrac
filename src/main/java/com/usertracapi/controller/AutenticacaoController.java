package com.usertracapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usertracapi.dto.AutenticacaoDTO;
import com.usertracapi.dto.LoginResponse;
import com.usertracapi.dto.UsuarioDTO;
import com.usertracapi.model.Usuario;
import com.usertracapi.repository.UsuarioRepository;
import com.usertracapi.service.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
public class AutenticacaoController {

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid AutenticacaoDTO dto) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.login(), dto.senha());
        var auth = authenticationManager.authenticate(usernamePassword);
        
        var token = tokenService.gerarToken((Usuario) auth.getPrincipal());

        return ResponseEntity.status(HttpStatus.OK).body(new LoginResponse(token));
    }

    @PostMapping("/cadastro")
    public ResponseEntity<?> registerUser(@RequestBody @Valid UsuarioDTO user) {
       
        if(usuarioRepository.findByLogin(user.login()) != null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(user.senha());
        Usuario newUser = new Usuario(user.login(), encryptedPassword, user.status());

        this.usuarioRepository.save(newUser);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

}
