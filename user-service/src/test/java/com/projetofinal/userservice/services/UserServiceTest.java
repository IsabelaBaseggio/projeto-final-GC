package com.projetofinal.userservice.services;

import com.projetofinal.userservice.models.classes.UserModel;
import com.projetofinal.userservice.models.exceptions.UserExists;
import com.projetofinal.userservice.models.exceptions.UserNotFoud;
import com.projetofinal.userservice.models.records.RequestUser;
import com.projetofinal.userservice.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.AssertJUnit.assertEquals;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testAddUser() {
        RequestUser requestUser = new RequestUser("Ana", 20, "ana@email.com", "adc123");
        UserModel user = new UserModel(requestUser); // @user16546
        String email = "ana@email.com";

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        when(userRepository.save(user)).thenReturn(user);

        UserModel resultado = userService.addUser(requestUser); // @user475452

        verify(userRepository).save(resultado);

        // assertEquals(user, resultado); // @user16546 , // @user475452 -> erro pois são instâncias diferentes

        assertEquals(user.getNome_usuario(), resultado.getNome_usuario());
        assertEquals(user.getIdade(), resultado.getIdade());
        assertEquals(user.getEmail(), resultado.getEmail());
        assertEquals(user.getSenha(), resultado.getSenha());
    }

    @Test
    public void testDeleteUser() {
        RequestUser requestUser = new RequestUser("Ana", 20, "ana@email.com", "adc123");
        UserModel user = userService.addUser(requestUser);

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        String userResult = userService.deleteUser(user.getId());
        assertEquals(user.getNome_usuario(), userResult);
    }

    @Test
    public void testDeleteUser_notFound() {
        long id = 1l;

        when(userRepository.findById(id)).thenReturn(Optional.empty());

        //metododeverificação
        assertThrows(UserNotFoud.class, () -> {
            userService.deleteUser(id);
        });
    }

    @Test
    public void testUpadateUser() {
        RequestUser requestUser = new RequestUser("Ana", 20, "ana@email.com", "adc123");
        UserModel user = userService.addUser(requestUser);

        RequestUser requestUserData = new RequestUser("Juliaa", 29, "ana@email.com", "adc123");
        UserModel userUpData = new UserModel(requestUserData);
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        UserModel userResult = userService.updateUser(user.getId(), requestUserData);

        assertTrue(userResult.equals(userUpData));

    }

    @Test
    public void testeUpdateUser_notFound() {
        long id = 1l;
        RequestUser requestUser = new RequestUser("Ana", 20, "ana@email.com", "adc123");

        when(userRepository.findById(id)).thenReturn(Optional.empty());

        //metododeverificação
        assertThrows(UserNotFoud.class, () -> {
            userService.updateUser(id, requestUser);
        });
    }
}
