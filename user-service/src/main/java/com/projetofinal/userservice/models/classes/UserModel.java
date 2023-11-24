package com.projetofinal.userservice.models.classes;

import com.projetofinal.userservice.models.records.RequestUser;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Data
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

}
