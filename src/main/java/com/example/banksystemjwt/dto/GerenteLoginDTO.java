package com.example.banksystemjwt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@Data
@AllArgsConstructor
public class GerenteLoginDTO {

    @Valid
    @NotEmpty(message = "Campo de email deve ser fornecido e nao pode ser nulo!")
    @Email(message = "Formato de email invalido!")
    private String email;

    @Valid
    @NotEmpty(message = "Campo senha deve ser fornecido e nao pode ser nulo!")
    private String senha;
}
