package com.example.userservice.controller;

import com.example.userservice.dto.SecretaireDTO;
import com.example.userservice.service.SecretaireService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SecretaireControllerTest {

    @Mock
    private SecretaireService secretaireService;

    @InjectMocks
    private SecretaireController secretaireController;

    private SecretaireDTO secretaireDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Prepare a sample SecretaireDTO
        secretaireDTO = new SecretaireDTO();
        secretaireDTO.setId(1L);
        secretaireDTO.setNom("secretaire");
        secretaireDTO.setPrenom("secretary");
        secretaireDTO.setPassword("password");
        secretaireDTO.setEmail("secretary@example.com");
        secretaireDTO.setRole("SECRETARY");
        secretaireDTO.setLabel("test");
    }

    @Test
    void findAllSecretaires() {
        when(secretaireService.findAllSecretaires()).thenReturn(Arrays.asList(secretaireDTO));

        List<SecretaireDTO> result = secretaireController.findAllSecretaires();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(secretaireDTO, result.get(0));
        verify(secretaireService).findAllSecretaires();
    }

    @Test
    void findSecretaireById() {
        Long id = 1L;

        when(secretaireService.findSecretaireById(id)).thenReturn(secretaireDTO);

        SecretaireDTO result = secretaireController.findSecretaireById(id);

        assertNotNull(result);
        assertEquals(secretaireDTO.getId(), result.getId());
        assertEquals(secretaireDTO, result);
        verify(secretaireService).findSecretaireById(id);
    }

    @Test
    void saveSecretaire() {
        Long medecinId = 1L;

        when(secretaireService.saveSecretaire(secretaireDTO, medecinId)).thenReturn(secretaireDTO);

        SecretaireDTO result = secretaireController.saveSecretaire(secretaireDTO, medecinId);

        assertNotNull(result);
        assertEquals(secretaireDTO.getId(), result.getId());
        assertEquals(secretaireDTO, result);
        verify(secretaireService).saveSecretaire(secretaireDTO, medecinId);
    }

    @Test
    void updateSecretaire() {
        Long id = 1L;

        // Prepare an updated SecretaireDTO
        SecretaireDTO updatedSecretaire = new SecretaireDTO();
        updatedSecretaire.setId(id);
        updatedSecretaire.setPrenom("updatedSecretary");
        updatedSecretaire.setPassword("newPassword");
        updatedSecretaire.setEmail("updated@example.com");
        updatedSecretaire.setRole("SECRETARY");
        updatedSecretaire.setLabel("test");

        // Mock behavior for updating the secretaire
        when(secretaireService.updateSecretaire(id, updatedSecretaire)).thenReturn(updatedSecretaire);

        // Call the method to test
        SecretaireDTO result = secretaireController.updateSecretaire(id, updatedSecretaire);

        // Verify the result and interactions
        assertNotNull(result);
        assertEquals(updatedSecretaire.getId(), result.getId());
        assertEquals(updatedSecretaire.getPrenom(), result.getPrenom());
        assertEquals(updatedSecretaire.getEmail(), result.getEmail());
        assertEquals(updatedSecretaire.getPassword(), result.getPassword());
        verify(secretaireService).updateSecretaire(id, updatedSecretaire);
    }

    @Test
    void deleteSecretaire() {
        Long id = 1L;

        when(secretaireService.deleteSecretaire(id)).thenReturn(true);

        Boolean result = secretaireController.deleteSecretaire(id);

        assertTrue(result);
        verify(secretaireService).deleteSecretaire(id);
    }

    @Test
    void findByLabelSec() {
        String label = "test";

        when(secretaireService.findByLabelContainingIgnoreCase(label)).thenReturn(Arrays.asList(secretaireDTO));

        List<SecretaireDTO> result = secretaireController.findByLabelSec(label);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(secretaireDTO, result.get(0));
        verify(secretaireService).findByLabelContainingIgnoreCase(label);
    }

    @Test
    void findByPrenomSec() {
        String prenom = "secretary";

        when(secretaireService.findByPrenomContainingIgnoreCase(prenom)).thenReturn(Arrays.asList(secretaireDTO));

        List<SecretaireDTO> result = secretaireController.findByPrenomSec(prenom);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(secretaireDTO, result.get(0));
        verify(secretaireService).findByPrenomContainingIgnoreCase(prenom);
    }

    @Test
    void findByMedecin() {
        Long medecinId = 1L;

        when(secretaireService.findByMedecin(medecinId)).thenReturn(Arrays.asList(secretaireDTO));

        List<SecretaireDTO> result = secretaireController.findByMedecin(medecinId);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(secretaireDTO, result.get(0));
        verify(secretaireService).findByMedecin(medecinId);
    }
}
