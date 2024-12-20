package com.example.userservice.service.impl;


import com.example.userservice.dto.MedecinDTO;
import com.example.userservice.dto.SecretaireDTO;
import com.example.userservice.entities.Medecin;
import com.example.userservice.entities.Secretaire;
import com.example.userservice.mapper.SecretaireMapper;
import com.example.userservice.repository.MedecinRepository;
import com.example.userservice.repository.SecretaireRepository;
import com.example.userservice.service.SecretaireService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SecretaireServiceImpl implements SecretaireService {

    private final SecretaireRepository secretaireRepository;
    private final SecretaireMapper secretaireMapper;
    private final MedecinRepository medecinRepository;
    private final PasswordEncoder passwordEncoder;

    public SecretaireServiceImpl(MedecinRepository medecinRepository,PasswordEncoder passwordEncoder, SecretaireRepository secretaireRepository, SecretaireMapper secretaireMapper) {
        this.medecinRepository = medecinRepository;
        this.secretaireRepository = secretaireRepository;
        this.secretaireMapper = secretaireMapper;
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    public List<SecretaireDTO> findAllSecretaires() {
        return secretaireRepository.findAll().stream()
                .map(secretaireMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public SecretaireDTO findSecretaireById(Long id) {
        Secretaire secretaire = secretaireRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Secretaire not found"));
        return secretaireMapper.toDto(secretaire);
    }

    @Override
    public SecretaireDTO saveSecretaire(SecretaireDTO secretaireDTO, Long medecinId) {

        Secretaire secretaire = secretaireMapper.toEntity(secretaireDTO, medecinId);
        Secretaire savedSecretaire = secretaireRepository.save(secretaire);
        return secretaireMapper.toDto(savedSecretaire);
    }

    @Override
    public SecretaireDTO updateSecretaire(Long id, SecretaireDTO secretaireDTO) {
        Secretaire existingSecretaire = secretaireRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Secretaire not found"));
        existingSecretaire.setNom(secretaireDTO.getNom());
        existingSecretaire.setPrenom(secretaireDTO.getPrenom());
        existingSecretaire.setEmail(secretaireDTO.getEmail());
        existingSecretaire.setPassword(passwordEncoder.encode(secretaireDTO.getPassword()));
        existingSecretaire.setRole(secretaireDTO.getRole());
        existingSecretaire.setLabel(secretaireDTO.getLabel());
        Secretaire updatedSecretaire= secretaireRepository.save(existingSecretaire);
        return secretaireMapper.toDto(updatedSecretaire);
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
    public Boolean deleteSecretaire(Long id) {
        Secretaire secretaire = secretaireRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medecin not found"));
        secretaireRepository.delete(secretaire);
        return true;
    }

    @Override
    public List<SecretaireDTO> findByLabelContainingIgnoreCase(String label) {
        return secretaireRepository.findByLabelContainingIgnoreCase(label).stream()
                .map(secretaireMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SecretaireDTO> findByPrenomContainingIgnoreCase(String prenom) {
        return secretaireRepository.findByPrenomContainingIgnoreCase(prenom).stream()
                .map(secretaireMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SecretaireDTO> findByMedecin(Long id) {
        Medecin medecin = new Medecin();
        medecin.setId(id);
        return secretaireRepository.findByMedecin(medecin).stream()
                .map(secretaireMapper::toDto)
                .collect(Collectors.toList());
    }



}
