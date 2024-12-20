package com.example.userservice.service;

import com.example.userservice.dto.MedecinDTO;

import java.util.List;

public interface MedecinService {


    List<MedecinDTO> findAllMedecins();
    MedecinDTO findMedecinById(Long id);
    //MedecinDTO saveMedecin(MedecinDTO medecinDTO);

    MedecinDTO saveMedecin(MedecinDTO medecinDTO, Long adminId);

    MedecinDTO updateMedecin(Long id, MedecinDTO medecinDTO);
    Boolean deleteMedecin(Long id);
    List<MedecinDTO> findByLabelContainingIgnoreCase(String label);
    List<MedecinDTO> findByPrenomContainingIgnoreCase(String prenom);
    List<MedecinDTO> findByAdmin(Long id);

    // New method to check if a Medecin exists
    boolean medecinExists(Long id);

}
