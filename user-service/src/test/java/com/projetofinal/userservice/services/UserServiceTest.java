package com.projetofinal.userservice.services;

import com.projetofinal.userservice.models.classes.UserModel;
import com.projetofinal.userservice.models.records.RequestUser;
import com.projetofinal.userservice.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddUser() {
        RequestUser requestUser = new RequestUser("Ana", 20, "ana@email.com", "adc123");
        UserModel user = new UserModel(requestUser);
        when(userRepository.save(user)).thenReturn(user);

        UserModel resultado = userService.addUser(requestUser);

        verify(userRepository).save(user);

        assertEquals(user, resultado);
    }
}
