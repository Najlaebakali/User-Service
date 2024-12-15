package com.example.userservice.controller;


import com.example.userservice.dto.AdminDTO;
import com.example.userservice.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @QueryMapping
    public List<AdminDTO> findAllAdmins() {
        return adminService.findAllAdmins();
    }

    @QueryMapping
    public AdminDTO findAdminById(@Argument Long id) {
        return adminService.findAdminById(id);
    }

    @MutationMapping
    public AdminDTO saveAdmin(@Argument AdminDTO admin) {
        return adminService.saveAdmin(admin);
    }

    @MutationMapping
    public AdminDTO updateAdmin(@Argument Long id, @Argument AdminDTO admin) {
        return adminService.updateAdmin(id, admin);
    }

    @MutationMapping
    public Boolean deleteAdmin(@Argument Long id) {
        return adminService.deleteAdmin(id);
    }

}
