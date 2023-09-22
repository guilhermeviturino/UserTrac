package com.usertracapi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
import com.usertracapi.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
public class AutenticacaoController {
    

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid AutenticacaoDTO dto){
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.getLogin(), dto.getSenha());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((Usuario) auth.getPrincipal());

        return ResponseEntity.status(HttpStatus.OK).body(new LoginResponse(token));

    }


    @PostMapping("/cadastrar")
    public ResponseEntity<String> registerUser(@RequestBody @Valid UsuarioDTO usuario){
        if(this.usuarioRepository.findByLogin(usuario.getLogin()) != null){
            return ResponseEntity.badRequest().build();
        }
        this.userService.registerUser(usuario);
        return ResponseEntity.ok().body("Usuário cadastrado com sucesso!");
    }

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;
    
}
