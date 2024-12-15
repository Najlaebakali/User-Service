package com.example.userservice.service;

import com.example.userservice.dto.AdminDTO;

import java.util.List;

public interface AdminService {
    List<AdminDTO> findAllAdmins();

    AdminDTO findAdminById(Long id);

    AdminDTO saveAdmin(AdminDTO adminDTO);

    AdminDTO updateAdmin(Long id, AdminDTO adminDTO);

    Boolean deleteAdmin(Long id);
}
