package com.projetofinal.userservice.models.exceptions;

public class UserExists extends RuntimeException{
    public UserExists(String message){
        super(message);
    }
}
