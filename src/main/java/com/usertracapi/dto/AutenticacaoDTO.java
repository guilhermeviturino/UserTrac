package com.usertracapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutenticacaoDTO {
    
    @NotNull
    @NotBlank
    private String login;


    @NotNull
    @NotBlank
    private String senha;
}
