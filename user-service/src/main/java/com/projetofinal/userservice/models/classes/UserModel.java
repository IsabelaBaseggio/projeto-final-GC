package com.projetofinal.userservice.models.classes;

import com.projetofinal.userservice.models.records.RequestUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Entity
// @NoArgsConstructor
@Table(name = "usuarios")
public class UserModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Getter
    private long id;

    //@Getter @Setter
    private String nome_usuario;

    //@Getter @Setter
    private Integer idade;

    // @Getter @Setter
    private String email;

    // @Getter @Setter
    private String senha;

    public UserModel(){}

    public UserModel(RequestUser requestUser){
        this.nome_usuario = requestUser.nome_usuario();
        this.idade = requestUser.idade();
        this.email = requestUser.email();
        this.senha = requestUser.senha();
    }

    public long getId() {
        return id;
    }

    public String getNome_usuario() {
        return nome_usuario;
    }

    public Integer getIdade() {
        return idade;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
