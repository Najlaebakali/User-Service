package com.example.userservice.service.impl;

import com.example.userservice.dto.AdminDTO;
import com.example.userservice.dto.UserDTO;

import com.example.userservice.dto.MedecinDTO;

import com.example.userservice.dto.SecretaireDTO;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    private AdminRepository adminRepository;

    private MedecinRepository medecinRepository;

    private SecretaireRepository secretaireRepository;
    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    private AdminMapper adminMapper;

    private MedecinMapper medecinMapper;
    private SecretaireMapper secretaireMapper;

    public UserServiceImpl(UserRepository repository, UserMapper userMapper,AdminMapper adminMapper,
                           AdminRepository adminRepository,MedecinRepository medecinRepository,
                           SecretaireRepository secretaireRepository,MedecinMapper medecinMapper,
                           SecretaireMapper secretaireMapper,PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.userMapper = userMapper;
        this.passwordEncoder=passwordEncoder;
        this.adminRepository = adminRepository;
        this.medecinRepository = medecinRepository;
        this.secretaireRepository = secretaireRepository;
        this.adminMapper = adminMapper;
        this.medecinMapper = medecinMapper;
        this.secretaireMapper = secretaireMapper;
    }

    @Override
    public List<UserDTO> findAllUsers() {
        return repository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findUserById(Long id) {
        Useer user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toDto(user);
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        if (userDTO == null) {
            throw new IllegalArgumentException("UserDTO cannot be null");
        }
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        Useer user = userMapper.toEntity(userDTO);
        Useer savedUser = repository.save(user);
        return userMapper.toDto(savedUser);
    }

    @Override
    public AdminDTO saveAdmin(AdminDTO adminDTO) {
        adminDTO.setPassword(passwordEncoder.encode(adminDTO.getPassword()));
        Admin admin = adminMapper.toEntity(adminDTO);
        Admin savedAdmin = adminRepository.save(admin);
        repository.save(admin);
        System.out.println("Admin saved with hashed password: " + savedAdmin.getPassword());
        return adminMapper.toDto(savedAdmin);
    }

    @Override
    public MedecinDTO saveMedecin(MedecinDTO medecinDTO, Long adminId) {
        medecinDTO.setPassword(passwordEncoder.encode(medecinDTO.getPassword()));
        Medecin medecin = medecinMapper.toEntity(medecinDTO, adminId);
        Medecin savedMedecin = medecinRepository.save(medecin);
        repository.save(medecin);
        return medecinMapper.toDto(savedMedecin);
    }

    @Override
    public SecretaireDTO saveSecretaire(SecretaireDTO secretaireDTO, Long medecinId) {
        secretaireDTO.setPassword(passwordEncoder.encode(secretaireDTO.getPassword()));
        Secretaire secretaire = secretaireMapper.toEntity(secretaireDTO, medecinId);
        Secretaire savedSecretaire = secretaireRepository.save(secretaire);
        repository.save(secretaire);
        return secretaireMapper.toDto(savedSecretaire);
    }



    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        Useer existingUser = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userMapper.updateEntityFromDto(userDTO, existingUser);
        Useer updatedUser = repository.save(existingUser);
        return userMapper.toDto(updatedUser);
    }

    @Override
    public Boolean deleteUser(Long id) {
        Useer user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        repository.delete(user);
        return true;
    }

    @Override
    public UserDTO findByEmail(String email) {
        return userMapper.toDto(repository.findByEmail(email));
    }


}
