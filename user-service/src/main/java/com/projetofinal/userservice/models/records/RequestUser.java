package com.projetofinal.userservice.models.records;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public record RequestUser(@NotBlank(message = "O campo 'nome' não pode estar vazio.") String nome_usuario,
                          @NotNull(message = "O campo 'idade' não pode estar vazio.") Integer idade,
                          @NotBlank(message = "O campo 'email' não pode estar vazio.") String email,
                          @NotBlank(message = "O campo 'senha' não pode estar vazio.") String senha) {

    @Override
    public String nome_usuario() {
        return nome_usuario;
    }

    @Override
    public Integer idade() {
        return idade;
    }

    @Override
    public String email() {
        return email;
    }

    @Override
    public String senha() {
        return senha;
    }
}