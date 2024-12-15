package com.example.userservice.mapper;
import com.example.userservice.dto.MedecinDTO;
import com.example.userservice.entities.Admin;
import com.example.userservice.entities.Medecin;
import com.example.userservice.repository.AdminRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedecinMapper {

    private final AdminRepository adminRepository;
    private AdminMapper adminMapper;
    @Autowired
    public MedecinMapper(AdminRepository adminRepository, AdminMapper adminMapper)
    {
        this.adminRepository = adminRepository;
        this.adminMapper = adminMapper;
    }
    public MedecinDTO toDto(Medecin medecin)
    {
        MedecinDTO medecinDTO = new MedecinDTO();
        BeanUtils.copyProperties(medecin, medecinDTO);
        medecinDTO.setAdmin(adminMapper.toDto(medecin.getAdmin()));
        return medecinDTO;
    }
    public Medecin toEntity(MedecinDTO medecinDTO, Long adminId) {
        Medecin medecin = new Medecin();
        BeanUtils.copyProperties(medecinDTO, medecin);
        if (adminId != null) {
            Admin admin = adminRepository.findById(adminId)
                    .orElseThrow(() -> new RuntimeException("Admin not found"));
            medecin.setAdmin(admin);
        }
        else {
            throw new RuntimeException("Admin ID is required for Medecin");
        }
        return medecin; }
   /* private AdminMapper adminMapper;
    private final AdminRepository adminRepository;

    public MedecinMapper(AdminRepository adminRepository,AdminMapper adminMapper) {
        this.adminRepository = adminRepository;
        this.adminMapper = adminMapper;
    }

    public MedecinDTO toDto(Medecin medecin) {
        MedecinDTO medecinDTO = new MedecinDTO();
        BeanUtils.copyProperties(medecin, medecinDTO);
        medecinDTO.setAdmin(adminMapper.toDto(medecin.getAdmin()));
        return medecinDTO;
    }

    public Medecin toEntity(MedecinDTO medecinDTO) {
        Medecin medecin = new Medecin();
        BeanUtils.copyProperties(medecinDTO, medecin);
        medecin.setAdmin(adminMapper.toEntity(medecinDTO.getAdmin()));
        if (medecinDTO.getAdmin() != null && medecinDTO.getAdmin().getId() != null)
        {
            Admin admin = adminRepository.findById(medecinDTO.getAdmin().getId())
                .orElseThrow(() -> new RuntimeException("Admin not found"));
            medecin.setAdmin(admin); }
        return medecin;
    }
    // Converts a Medecin entity to a MedecinDTO
    public MedecinDTO toDto(Medecin medecin) {
        if (medecin == null) {
            return null;
        }
        MedecinDTO dto = new MedecinDTO();
        dto.setId(medecin.getId());
        dto.setNom(medecin.getNom());
        dto.setPrenom(medecin.getPrenom());
        dto.setEmail(medecin.getEmail());
        dto.setRole(medecin.getRole());
        dto.setLabel(medecin.getLabel());
        // Map Admin entity to AdminDTO if present
        if (medecin.getAdmin() != null) {
            dto.setAdmin(AdminDTO.fromEntity(medecin.getAdmin())); // Static method in AdminDTO
        }
        return dto;
    }

    // Converts a MedecinDTO to a Medecin entity
    public Medecin toEntity(MedecinDTO dto) {
        if (dto == null) {
            return null;
        }
        Medecin medecin = new Medecin();
        medecin.setId(dto.getId());
        medecin.setNom(dto.getNom());
        medecin.setPrenom(dto.getPrenom());
        medecin.setEmail(dto.getEmail());
        medecin.setRole(dto.getRole());
        medecin.setLabel(dto.getLabel());
        // Map AdminDTO to Admin entity if present
        if (dto.getAdmin() != null) {
            medecin.setAdmin(Admin.fromDto(dto.getAdmin())); // Static method in Admin
        }
        return medecin;
    }

    // Updates an existing Medecin entity from a MedecinDTO
    public void updateEntityFromDto(MedecinDTO dto, Medecin entity) {
        if (dto == null || entity == null) {
            return;
        }
        entity.setNom(dto.getNom());
        entity.setPrenom(dto.getPrenom());
        entity.setEmail(dto.getEmail());
        entity.setRole(dto.getRole());
        entity.setLabel(dto.getLabel());
        // Update Admin entity if AdminDTO is provided
        if (dto.getAdmin() != null) {
            entity.setAdmin(Admin.fromDto(dto.getAdmin())); // Static method in Admin
        }
    }*/

}
