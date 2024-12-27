package com.example.userservice.service.impl;

import com.example.userservice.dto.AdminDTO;
import com.example.userservice.dto.MedecinDTO;
import com.example.userservice.dto.SecretaireDTO;
import com.example.userservice.dto.UserDTO;
import com.example.userservice.entities.Useer;
import com.example.userservice.entities.Admin;
import com.example.userservice.entities.Medecin;
import com.example.userservice.entities.Secretaire;
import com.example.userservice.mapper.AdminMapper;
import com.example.userservice.mapper.MedecinMapper;
import com.example.userservice.mapper.SecretaireMapper;
import com.example.userservice.mapper.UserMapper;
import com.example.userservice.repository.AdminRepository;
import com.example.userservice.repository.MedecinRepository;
import com.example.userservice.repository.SecretaireRepository;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AdminRepository adminRepository;

    @Mock
    private MedecinRepository medecinRepository;

    @Mock
    private SecretaireRepository secretaireRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private AdminMapper adminMapper;

    @Mock
    private MedecinMapper medecinMapper;

    @Mock
    private SecretaireMapper secretaireMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    private UserDTO userDTO;
    private Useer user;
    private AdminDTO adminDTO;
    private Admin admin;
    private MedecinDTO medecinDTO;
    private Medecin medecin;
    private SecretaireDTO secretaireDTO;
    private Secretaire secretaire;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Prepare a sample UserDTO and Useer
        userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setNom("user");
        userDTO.setPrenom("user");
        userDTO.setPassword("password");
        userDTO.setEmail("user@example.com");
        userDTO.setRole("USER");

        user = new Useer();
        user.setId(1L);
        user.setNom("user");
        user.setPrenom("user");
        user.setPassword("password");
        user.setEmail("user@example.com");
        user.setRole("USER");

        // Prepare a sample AdminDTO and Admin
        adminDTO = new AdminDTO();
        adminDTO.setId(1L);
        adminDTO.setNom("admin");
        adminDTO.setPrenom("admin");
        adminDTO.setPassword("adminpassword");
        adminDTO.setEmail("admin@example.com");
        adminDTO.setRole("ADMIN");
        adminDTO.setLabel("adminlabel");

        admin = new Admin();
        admin.setId(1L);
        admin.setNom("admin");
        admin.setPrenom("admin");
        admin.setPassword("adminpassword");
        admin.setEmail("admin@example.com");
        admin.setRole("ADMIN");
        admin.setLabel("adminlabel");

        // Prepare a sample MedecinDTO and Medecin
        medecinDTO = new MedecinDTO();
        medecinDTO.setId(1L);
        medecinDTO.setNom("medecin");
        medecinDTO.setPrenom("medecin");
        medecinDTO.setPassword("medecinpassword");
        medecinDTO.setEmail("medecin@example.com");
        medecinDTO.setRole("MEDECIN");
        medecinDTO.setLabel("medecinlabel");

        medecin = new Medecin();
        medecin.setId(1L);
        medecin.setNom("medecin");
        medecin.setPrenom("medecin");
        medecin.setPassword("medecinpassword");
        medecin.setEmail("medecin@example.com");
        medecin.setRole("MEDECIN");
        medecin.setLabel("medecinlabel");

        // Prepare a sample SecretaireDTO and Secretaire
        secretaireDTO = new SecretaireDTO();
        secretaireDTO.setId(1L);
        secretaireDTO.setNom("secretaire");
        secretaireDTO.setPrenom("secretaire");
        secretaireDTO.setPassword("secretairepassword");
        secretaireDTO.setEmail("secretaire@example.com");
        secretaireDTO.setRole("SECRETARY");
        secretaireDTO.setLabel("secretairelabel");

        secretaire = new Secretaire();
        secretaire.setId(1L);
        secretaire.setNom("secretaire");
        secretaire.setPrenom("secretaire");
        secretaire.setPassword("secretairepassword");
        secretaire.setEmail("secretaire@example.com");
        secretaire.setRole("SECRETARY");
        secretaire.setLabel("secretairelabel");
    }

    @Test
    void findAllUsers() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(user));
        when(userMapper.toDto(user)).thenReturn(userDTO);

        List<UserDTO> result = userServiceImpl.findAllUsers();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(userDTO, result.get(0));
        verify(userRepository).findAll();
    }

    @Test
    void findUserById() {
        Long id = 1L;

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(userMapper.toDto(user)).thenReturn(userDTO);

        UserDTO result = userServiceImpl.findUserById(id);

        assertNotNull(result);
        assertEquals(userDTO.getId(), result.getId());
        assertEquals(userDTO, result);
        verify(userRepository).findById(id);
    }

    @Test
    void saveUser() {
        when(userMapper.toEntity(userDTO)).thenReturn(user);
        when(passwordEncoder.encode(userDTO.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(userDTO);

        UserDTO result = userServiceImpl.saveUser(userDTO);

        assertNotNull(result);
        assertEquals(userDTO.getId(), result.getId());
        assertEquals(userDTO, result);
        verify(userRepository).save(user);
    }

    @Test
    void updateUser() {
        Long id = 1L;

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        // No need to stub `userMapper.updateEntityFromDto` since it's a void method.
        // Call the method directly in the test to make sure it modifies the user entity as expected.
        doNothing().when(userMapper).updateEntityFromDto(userDTO, user);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(userDTO);

        UserDTO result = userServiceImpl.updateUser(id, userDTO);

        assertNotNull(result);
        assertEquals(userDTO.getId(), result.getId());
        assertEquals(userDTO, result);
        verify(userRepository).findById(id);
        verify(userMapper).updateEntityFromDto(userDTO, user); // Verify that the update method is called
        verify(userRepository).save(user);
    }


    @Test
    void deleteUser() {
        Long id = 1L;

        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        Boolean result = userServiceImpl.deleteUser(id);

        assertTrue(result);
        verify(userRepository).delete(user);
    }

    @Test
    void findByEmail() {
        String email = "user@example.com";

        when(userRepository.findByEmail(email)).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(userDTO);

        UserDTO result = userServiceImpl.findByEmail(email);

        assertNotNull(result);
        assertEquals(userDTO.getId(), result.getId());
        assertEquals(userDTO, result);
        verify(userRepository).findByEmail(email);
    }

}