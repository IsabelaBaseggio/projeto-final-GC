package com.projetofinal.userservice.controllers;

import com.projetofinal.userservice.models.classes.UserModel;
import com.projetofinal.userservice.models.exceptions.UserExists;
import com.projetofinal.userservice.models.records.RequestUser;
import com.projetofinal.userservice.services.UserService;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity listUsers() {
        List<UserModel> users = userService.listUsers();
        return ResponseEntity.status(HttpStatus.FOUND).body(users);
    }


    //MÉTODO ADICIONAR
    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody RequestUser requestUser, BindingResult result) {
        try {
            UserModel userAdded = userService.addUser(requestUser);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuário criado com sucesso");
        } catch (UserExists e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }


    }

   // MÉTODO ATUALIZAR
    @PutMapping("/{id}/update")
    public ResponseEntity updateUser(@PathVariable(value = "id") long id, @Valid @RequestBody RequestUser requestUser, BindingResult result) {
        try {

            UserModel userUpdate = userService.updateUser(id, requestUser);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuário atualizado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }


    //MÉTODO DELETAR
    @DeleteMapping("/{id}/delete")
   public ResponseEntity<String> deleteUser(@PathVariable long id) {
       try {
       String user = userService.deleteUser(id);
           return ResponseEntity.ok().body("Usuário deletado com sucesso!");
       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


}
