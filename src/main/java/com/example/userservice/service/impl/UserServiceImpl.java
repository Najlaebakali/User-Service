package com.example.userservice.service.impl;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.entities.Useer;
import com.example.userservice.mapper.UserMapper;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository, UserMapper userMapper,PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.userMapper = userMapper;
        this.passwordEncoder=passwordEncoder;
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
