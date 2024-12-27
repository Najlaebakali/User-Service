package com.example.userservice.controller;

import com.example.userservice.dto.AdminDTO;
import com.example.userservice.service.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AdminControllerTest {

    @Mock
    private AdminService adminService;

    @InjectMocks
    private AdminController adminController;

    private AdminDTO adminDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Prepare a sample AdminDTO
        adminDTO = new AdminDTO();
        adminDTO.setId(1L);
        adminDTO.setNom("testad");
        adminDTO.setPrenom("testad");
        adminDTO.setPassword("1234");
        adminDTO.setEmail("admin@gmail.com");
        adminDTO.setRole("ADMIN");
        adminDTO.setLabel("testtast");
    }

    @Test
    void findAllAdmins() {
        when(adminService.findAllAdmins()).thenReturn(Arrays.asList(adminDTO));

        List<AdminDTO> result = adminController.findAllAdmins();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(adminDTO, result.get(0));
        verify(adminService).findAllAdmins();
    }

    @Test
    void findAdminById() {
        Long id = 1L;

        when(adminService.findAdminById(id)).thenReturn(adminDTO);

        AdminDTO result = adminController.findAdminById(id);

        assertNotNull(result);
        assertEquals(adminDTO.getId(), result.getId());
        assertEquals(adminDTO, result);
        verify(adminService).findAdminById(id);

    }

    @Test
    void saveAdmin() {
        when(adminService.saveAdmin(adminDTO)).thenReturn(adminDTO);

        AdminDTO result = adminController.saveAdmin(adminDTO);

        assertNotNull(result);
        assertEquals(adminDTO.getId(), result.getId());
        assertEquals(adminDTO, result);
        verify(adminService).saveAdmin(adminDTO);
    }

    @Test
    void updateAdmin() {
        Long id = 1L;

        // Prepare an updated AdminDTO
        AdminDTO updatedAdmin = new AdminDTO();
        updatedAdmin.setId(id);
        updatedAdmin.setPrenom("updatedAdmin");
        updatedAdmin.setPassword("newPassword");
        updatedAdmin.setEmail("updated@example.com");
        updatedAdmin.setRole("ADMIN");
        updatedAdmin.setLabel("test");

        // Mock behavior for finding the admin by ID
        when(adminService.findAdminById(id)).thenReturn(adminDTO);

        // Mock behavior for updating the admin
        when(adminService.updateAdmin(id, updatedAdmin)).thenReturn(updatedAdmin);

        // Call the method to test
        AdminDTO result = adminController.updateAdmin(id, updatedAdmin);

        // Verify the result and interactions
        assertNotNull(result);
        assertEquals(updatedAdmin.getId(), result.getId());
        assertEquals(updatedAdmin.getPrenom(), result.getPrenom());
        assertEquals(updatedAdmin.getEmail(), result.getEmail());
        assertEquals(updatedAdmin.getPassword(), result.getPassword());

        // Verify that updateAdmin was invoked
        verify(adminService).updateAdmin(id, updatedAdmin);
    }

    @Test
    void deleteAdmin() {
        Long id = 1L;

        when(adminService.deleteAdmin(id)).thenReturn(true);

        Boolean result = adminController.deleteAdmin(id);

        assertTrue(result);
        verify(adminService).deleteAdmin(id);
    }
}
