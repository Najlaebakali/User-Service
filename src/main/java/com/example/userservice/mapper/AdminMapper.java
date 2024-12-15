package com.example.userservice.mapper;


import com.example.userservice.dto.AdminDTO;
import com.example.userservice.entities.Admin;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {

    public AdminDTO toDto(Admin admin) {
        if (admin == null) {
            return null;
        }
        AdminDTO dto = new AdminDTO();
        dto.setId(admin.getId());
        dto.setNom(admin.getNom());
        dto.setPrenom(admin.getPrenom());
        dto.setEmail(admin.getEmail());
        dto.setPassword(admin.getPassword());
        dto.setRole(admin.getRole());
        dto.setLabel(admin.getLabel());
        return dto;
    }

    public Admin toEntity(AdminDTO dto) {
        if (dto == null) {
            return null;
        }
        Admin admin = new Admin();
        admin.setId(dto.getId());
        admin.setNom(dto.getNom());
        admin.setPrenom(dto.getPrenom());
        admin.setEmail(dto.getEmail());
        admin.setPassword(dto.getPassword());
        admin.setRole(dto.getRole());
        admin.setLabel(dto.getLabel());
        return admin;
    }

    public void updateEntityFromDto(AdminDTO dto, Admin entity) {
        if (dto == null || entity == null) {
            return;
        }
        entity.setNom(dto.getNom());
        entity.setPrenom(dto.getPrenom());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword()); // Mise à jour du mot de passe
        entity.setRole(dto.getRole());
        entity.setLabel(dto.getLabel());
    }



}
