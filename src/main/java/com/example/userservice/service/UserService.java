package com.example.userservice.service;

import com.example.userservice.dto.AdminDTO;
import com.example.userservice.dto.MedecinDTO;
import com.example.userservice.dto.SecretaireDTO;
import com.example.userservice.dto.UserDTO;

import java.util.List;

public interface UserService {


    List<UserDTO> findAllUsers();
    UserDTO findUserById(Long id);
    UserDTO saveUser(UserDTO userDTO);
    AdminDTO saveAdmin(AdminDTO adminDTO);
    MedecinDTO saveMedecin(MedecinDTO medecinDTO, Long adminId);
    SecretaireDTO saveSecretaire(SecretaireDTO secretaireDTO, Long medecinId);
    UserDTO updateUser(Long id, UserDTO userDTO);
    Boolean deleteUser(Long id);

    UserDTO findByEmail(String email);
}
