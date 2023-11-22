package com.projetofinal.userservice.controllers;

import com.projetofinal.userservice.models.classes.UserModel;
import com.projetofinal.userservice.models.exceptions.UserExists;
import com.projetofinal.userservice.models.records.RequestUser;
import com.projetofinal.userservice.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity listUsers(){
        List<UserModel> users = userService.listUsers();
        return ResponseEntity.status(HttpStatus.FOUND).body(users);
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody RequestUser requestUser){
        try{
            UserModel userAdded = userService.addUser(requestUser);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuário criado com sucesso");
        } catch (UserExists e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }

}
