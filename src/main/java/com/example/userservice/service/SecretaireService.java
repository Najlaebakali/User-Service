package com.example.userservice.service;

import com.example.userservice.dto.MedecinDTO;
import com.example.userservice.dto.SecretaireDTO;

import java.util.List;

public interface SecretaireService {


    List<SecretaireDTO> findAllSecretaires();
    SecretaireDTO findSecretaireById(Long id);
    //MedecinDTO saveMedecin(MedecinDTO medecinDTO);

    SecretaireDTO saveSecretaire(SecretaireDTO secretaireDTO, Long medecinId);

    //MedecinDTO updateMedecin(Long id, MedecinDTO medecinDTO);
    Boolean deleteSecretaire(Long id);
    List<SecretaireDTO> findByLabelContainingIgnoreCase(String label);
    List<SecretaireDTO> findByPrenomContainingIgnoreCase(String prenom);
    List<SecretaireDTO> findByMedecin(Long id);
}
