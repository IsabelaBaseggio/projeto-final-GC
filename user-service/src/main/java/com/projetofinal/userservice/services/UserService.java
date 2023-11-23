package com.projetofinal.userservice.services;

import com.projetofinal.userservice.models.classes.UserModel;
import com.projetofinal.userservice.models.exceptions.UserExists;
import com.projetofinal.userservice.models.exceptions.UserNotFoud;
import com.projetofinal.userservice.models.records.RequestUser;
import com.projetofinal.userservice.repositories.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<UserModel> listUsers() {
        return userRepository.findAll();
    }

    public UserModel addUser(RequestUser requestUser) {
        UserModel userModel = new UserModel(requestUser);
        Optional<UserModel> user = userRepository.findByEmail(userModel.getEmail());
        if (user.isPresent()) {
            throw new UserExists("Email já cadastrado!");
        }
        userRepository.save(userModel);
        return userModel;
    }

    public void deleteUser(long id) {
        Optional<UserModel> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
        } else {
            throw new UserExists("Usuário não encontrado!");
        }

    }


    public UserModel updateUser(long id, UserModel updateUser) {
        Optional<UserModel> userModelOptional = userRepository.findById(id);
        if (userModelOptional.isPresent()) {
            UserModel userExists = userModelOptional.get();

            userExists.setNome_usuario(updateUser.getNome_usuario());
            userExists.setIdade(updateUser.getIdade());
            userExists.setSenha(updateUser.getSenha());

            userRepository.save(userExists);

            return userExists;
        }
        throw new UserNotFoud("Usário não encontrado!");
    }
}
