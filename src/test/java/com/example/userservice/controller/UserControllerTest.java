package com.example.userservice.controller;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Préparation d'un exemple de UserDTO
        userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setNom("user");
        userDTO.setPrenom("user");
        userDTO.setPassword("password");
        userDTO.setEmail("user@example.com");
        userDTO.setRole("USER");
    }

    @Test
    void findAllUsers() {
        when(userService.findAllUsers()).thenReturn(Arrays.asList(userDTO));

        List<UserDTO> result = userController.findAllUsers();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(userDTO, result.get(0));
        verify(userService).findAllUsers();
    }

    @Test
    void findUserById() {
        Long id = 1L;

        when(userService.findUserById(id)).thenReturn(userDTO);

        UserDTO result = userController.findUserById(id);

        assertNotNull(result);
        assertEquals(userDTO.getId(), result.getId());
        assertEquals(userDTO, result);
        verify(userService).findUserById(id);
    }

    @Test
    void saveUser() {
        when(userService.saveUser(userDTO)).thenReturn(userDTO);

        UserDTO result = userController.saveUser(userDTO);

        assertNotNull(result);
        assertEquals(userDTO.getId(), result.getId());
        assertEquals(userDTO, result);
        verify(userService).saveUser(userDTO);
    }

    @Test
    void updateUser() {
        Long id = 1L;

        // Préparation d'un UserDTO mis à jour
        UserDTO updatedUser = new UserDTO();
        updatedUser.setId(id);
        updatedUser.setPrenom("updatedUser");
        updatedUser.setPassword("newPassword");
        updatedUser.setEmail("updated@example.com");
        updatedUser.setRole("USER");

        // Simulation du comportement pour trouver l'utilisateur par ID
        when(userService.findUserById(id)).thenReturn(userDTO);

        // Simulation du comportement pour mettre à jour l'utilisateur
        when(userService.updateUser(id, updatedUser)).thenReturn(updatedUser);

        // Appel de la méthode à tester
        UserDTO result = userController.updateUser(id, updatedUser);

        // Vérification du résultat et des interactions
        assertNotNull(result);
        assertEquals(updatedUser.getId(), result.getId());
        assertEquals(updatedUser.getPrenom(), result.getPrenom());
        assertEquals(updatedUser.getEmail(), result.getEmail());
        assertEquals(updatedUser.getPassword(), result.getPassword());

        // Vérification que updateUser a été invoqué
        verify(userService).updateUser(id, updatedUser);
    }

    @Test
    void deleteUser() {
        Long id = 1L;

        when(userService.deleteUser(id)).thenReturn(true);

        Boolean result = userController.deleteUser(id);

        assertTrue(result);
        verify(userService).deleteUser(id);
    }

    @Test
    void findUserByEmail() {
        String email = "user@example.com";

        when(userService.findByEmail(email)).thenReturn(userDTO);

        UserDTO result = userController.findUserByEmail(email);

    }
}