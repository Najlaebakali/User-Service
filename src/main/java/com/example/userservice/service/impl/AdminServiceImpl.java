package com.example.userservice.service.impl;

import com.example.userservice.dto.AdminDTO;
import com.example.userservice.entities.Admin;
import com.example.userservice.mapper.AdminMapper;
import com.example.userservice.repository.AdminRepository;
import com.example.userservice.service.AdminService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;
    private final PasswordEncoder passwordEncoder;

    public AdminServiceImpl(AdminRepository adminRepository, AdminMapper adminMapper,PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.adminMapper = adminMapper;
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    public List<AdminDTO> findAllAdmins() {
        return adminRepository.findAll().stream()
                .map(adminMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AdminDTO findAdminById(Long id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
        return adminMapper.toDto(admin);
    }

    @Override
    public AdminDTO saveAdmin(AdminDTO adminDTO) {
        if (adminDTO == null) {
            throw new IllegalArgumentException("UserDTO cannot be null");
        }

        adminDTO.setPassword(passwordEncoder.encode(adminDTO.getPassword()));
        Admin admin = adminMapper.toEntity(adminDTO);
        Admin savedAdmin = adminRepository.save(admin);
        return adminMapper.toDto(savedAdmin);
    }

    @Override
    public AdminDTO updateAdmin(Long id, AdminDTO adminDTO) {
        Admin existingAdmin = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found"));


        adminMapper.updateEntityFromDto(adminDTO, existingAdmin);
        Admin updatedAdmin = adminRepository.save(existingAdmin);
        return adminMapper.toDto(updatedAdmin);
    }

    @Override
    public Boolean deleteAdmin(Long id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
        adminRepository.delete(admin);
        return true;
    }


}
