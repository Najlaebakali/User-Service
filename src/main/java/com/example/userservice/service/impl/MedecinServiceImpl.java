package com.example.userservice.service.impl;

import com.example.userservice.dto.MedecinDTO;
import com.example.userservice.entities.Admin;
import com.example.userservice.entities.Medecin;
import com.example.userservice.mapper.MedecinMapper;
import com.example.userservice.repository.AdminRepository;
import com.example.userservice.repository.MedecinRepository;
import com.example.userservice.service.MedecinService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class MedecinServiceImpl implements MedecinService {

    private final MedecinRepository medecinRepository;
    private final MedecinMapper medecinMapper;
    private final AdminRepository adminRepository;

    public MedecinServiceImpl(MedecinRepository medecinRepository, MedecinMapper medecinMapper, AdminRepository adminRepository) {
        this.medecinRepository = medecinRepository;
        this.medecinMapper = medecinMapper;
        this.adminRepository = adminRepository;
    }

    @Override
    public List<MedecinDTO> findAllMedecins() {
        return medecinRepository.findAll().stream()
                .map(medecinMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MedecinDTO findMedecinById(Long id) {
        Medecin medecin = medecinRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medecin not found"));
        return medecinMapper.toDto(medecin);
    }

    @Override
    public MedecinDTO saveMedecin(MedecinDTO medecinDTO, Long adminId) {

        Medecin medecin = medecinMapper.toEntity(medecinDTO, adminId);
        Medecin savedMedecin = medecinRepository.save(medecin);
        return medecinMapper.toDto(savedMedecin);
    }
    @Override
    public boolean medecinExists(Long id) {
        return medecinRepository.existsById(id);
    }

    /*@Override
    public MedecinDTO updateMedecin(Long id, MedecinDTO medecinDTO) {
        Medecin existingMedecin = medecinRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medecin not found"));

        medecinMapper.updateEntityFromDto(medecinDTO, existingMedecin);
        Medecin updatedMedecin = medecinRepository.save(existingMedecin);
        return medecinMapper.toDto(updatedMedecin);
    }*/

    @Override
    public Boolean deleteMedecin(Long id) {
        Medecin medecin = medecinRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medecin not found"));
        medecinRepository.delete(medecin);
        return true;
    }

    @Override
    public List<MedecinDTO> findByLabelContainingIgnoreCase(String label) {
        return medecinRepository.findByLabelContainingIgnoreCase(label).stream()
                .map(medecinMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MedecinDTO> findByPrenomContainingIgnoreCase(String prenom) {
        return medecinRepository.findByPrenomContainingIgnoreCase(prenom).stream()
                .map(medecinMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MedecinDTO> findByAdmin(Long id) {
        Admin admin = new Admin();
        admin.setId(id);
        return medecinRepository.findByAdmin(admin).stream()
                .map(medecinMapper::toDto)
                .collect(Collectors.toList());
    }




}
