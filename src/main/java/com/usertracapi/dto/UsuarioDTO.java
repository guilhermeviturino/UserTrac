package com.usertracapi.dto;

import com.usertracapi.constants.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    
    private String login;

    private String senha;

    private Status status;
}
