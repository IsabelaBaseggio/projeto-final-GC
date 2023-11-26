package com.projetofinal.userservice.models.exceptions;

public class UserNotFoud extends RuntimeException {
    public UserNotFoud(String message) {
        super(message);
    }
}
