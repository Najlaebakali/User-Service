package com.example.userservice.mapper;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.entities.Useer;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDTO toDto(Useer user) {
        if (user == null) {
            return null;
        }
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setNom(user.getNom());
        dto.setPrenom(user.getPrenom());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setRole(user.getRole());
        return dto;
    }

    public Useer toEntity(UserDTO dto) {
        if (dto == null) {
            return null;
        }
        Useer user = new Useer();
        user.setId(dto.getId());
        user.setNom(dto.getNom());
        user.setPrenom(dto.getPrenom());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        return user;
    }

    public void updateEntityFromDto(UserDTO dto, Useer entity) {
        if (dto == null || entity == null) {
            return;
        }
        entity.setNom(dto.getNom());
        entity.setPrenom(dto.getPrenom());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setRole(dto.getRole());
    }

}
