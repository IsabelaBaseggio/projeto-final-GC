package com.projetofinal.userservice.services;

import com.projetofinal.userservice.models.classes.UserModel;
import com.projetofinal.userservice.models.exceptions.UserExists;
import com.projetofinal.userservice.models.exceptions.UserNotFoud;
import com.projetofinal.userservice.models.records.RequestUser;
import com.projetofinal.userservice.repositories.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
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

    public String deleteUser(long id) {
        Optional<UserModel> user = userRepository.findById(id);
        if (user.isPresent()) {
            String userName = user.get().getNome_usuario();
            userRepository.delete(user.get());

            return userName;
        } else {
            throw new UserNotFoud("Usário não encontrado!");
        }

    }


    public UserModel updateUser(long id,RequestUser  updateUser) {
        UserModel user = new UserModel(updateUser);
        Optional<UserModel> userModelOptional = userRepository.findById(id);
        if (userModelOptional.isPresent()) {
            UserModel userExists = userModelOptional.get();

            userExists.setNome_usuario(user.getNome_usuario());
            userExists.setIdade(user.getIdade());
            userExists.setSenha(user.getSenha());

            userRepository.save(userExists);

            return userExists;
        }
        throw new UserNotFoud("Usário não encontrado!");
    }
}
