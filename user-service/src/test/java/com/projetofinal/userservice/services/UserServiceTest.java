package com.projetofinal.userservice.services;

import com.projetofinal.userservice.models.classes.UserModel;
import com.projetofinal.userservice.models.exceptions.UserExists;
import com.projetofinal.userservice.models.records.RequestUser;
import com.projetofinal.userservice.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    public void testListUsers(){
        List<UserModel> users = new ArrayList<>();
        RequestUser requestUser = new RequestUser("Ana", 20, "ana@email.com", "adc123");
        UserModel user = new UserModel(requestUser);
        RequestUser requestUser2 = new RequestUser("Camila", 23, "camila@email.com", "def456");
        UserModel use2 = new UserModel(requestUser2);
        users.add(user);
        users.add(use2);

        UserModel userAdded = userService.addUser(requestUser);
        UserModel user2added = userService.addUser(requestUser2);

        when(userRepository.findAll()).thenReturn(users);

        List<UserModel> result = userService.listUsers();

        verify(userRepository).findAll();

        assertEquals(users, result);

        assertEquals(users.size(), result.size());
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

        assertTrue(user.equals(resultado));
    }

    @Test
    public void testAddUser_userExists(){
        RequestUser requestUser = new RequestUser("Ana", 20, "ana@email.com", "adc123");
        UserModel user = new UserModel(requestUser);
        String email = "ana@email.com";

        UserModel anaJaCadastrada = userService.addUser(requestUser);


        RequestUser requestUser2 = new RequestUser("Ana 2", 20, "ana@email.com", "adc123");

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        assertThrows(UserExists.class, () -> {
            userService.addUser(requestUser2);
        });

    }
}
