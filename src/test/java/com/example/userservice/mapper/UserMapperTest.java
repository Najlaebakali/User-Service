package com.example.userservice.mapper;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.entities.Useer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    private UserMapper userMapper;
    private Useer user;
    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        userMapper = new UserMapper();

        // Prepare a sample Useer
        user = new Useer();
        user.setId(1L);
        user.setNom("user");
        user.setPrenom("user");
        user.setEmail("user@example.com");
        user.setPassword("password");
        user.setRole("USER");

        // Prepare a sample UserDTO
        userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setNom("user");
        userDTO.setPrenom("user");
        userDTO.setEmail("user@example.com");
        userDTO.setPassword("password");
        userDTO.setRole("USER");
    }

    @Test
    void toDto() {
        UserDTO dto = userMapper.toDto(user);

        assertNotNull(dto);
        assertEquals(user.getId(), dto.getId());
        assertEquals(user.getNom(), dto.getNom());
        assertEquals(user.getPrenom(), dto.getPrenom());
        assertEquals(user.getEmail(), dto.getEmail());
        assertEquals(user.getPassword(), dto.getPassword());
        assertEquals(user.getRole(), dto.getRole());
    }

    @Test
    void toEntity() {
        Useer entity = userMapper.toEntity(userDTO);

        assertNotNull(entity);
        assertEquals(userDTO.getId(), entity.getId());
        assertEquals(userDTO.getNom(), entity.getNom());
        assertEquals(userDTO.getPrenom(), entity.getPrenom());
        assertEquals(userDTO.getEmail(), entity.getEmail());
        assertEquals(userDTO.getPassword(), entity.getPassword());
        assertEquals(userDTO.getRole(), entity.getRole());
    }

    @Test
    void updateEntityFromDto() {
        Useer entity = new Useer();
        entity.setId(1L);  // Set initial ID to ensure it's not overwritten

        userMapper.updateEntityFromDto(userDTO, entity);

        assertNotNull(entity);
        assertEquals(userDTO.getId(), entity.getId());  // ID should remain unchanged
        assertEquals(userDTO.getNom(), entity.getNom());
        assertEquals(userDTO.getPrenom(), entity.getPrenom());
        assertEquals(userDTO.getEmail(), entity.getEmail());
        assertEquals(userDTO.getPassword(), entity.getPassword());
        assertEquals(userDTO.getRole(), entity.getRole());
    }
}
