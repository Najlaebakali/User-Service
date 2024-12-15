package com.example.userservice.mapper;


import com.example.userservice.dto.AdminDTO;
import com.example.userservice.entities.Admin;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
//@Mapper(componentModel = "spring")
public interface AdminMapper {

    AdminDTO toDto(Admin admin);
    Admin toEntity(AdminDTO adminDTO);
}
