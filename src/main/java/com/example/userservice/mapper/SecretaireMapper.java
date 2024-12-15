package com.example.userservice.mapper;


import com.example.userservice.dto.SecretaireDTO;
import com.example.userservice.entities.Medecin;
import com.example.userservice.entities.Secretaire;
import com.example.userservice.repository.MedecinRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SecretaireMapper {


    private final MedecinRepository medecinRepository;
    private MedecinMapper medecinMapper;
    @Autowired
    public SecretaireMapper(MedecinRepository medecinRepository, MedecinMapper medecinMapper)
    {
        this.medecinRepository = medecinRepository;
        this.medecinMapper = medecinMapper;
    }
    public SecretaireDTO toDto(Secretaire secretaire)
    {
        SecretaireDTO secretaireDTO = new SecretaireDTO();
        BeanUtils.copyProperties(secretaire, secretaireDTO);
        secretaireDTO.setMedecin(medecinMapper.toDto(secretaire.getMedecin()));
        return secretaireDTO;
    }
    public Secretaire toEntity(SecretaireDTO secretaireDTO, Long medecinId) {
        Secretaire secretaire = new Secretaire();
        BeanUtils.copyProperties(secretaireDTO, secretaire);
        if (medecinId != null) {
            Medecin medecin = medecinRepository.findById(medecinId)
                    .orElseThrow(() -> new RuntimeException("Medecin not found"));
            secretaire.setMedecin(medecin);
        }
        else {
            throw new RuntimeException("Medecin ID is required for Secretaire");
        }
        return secretaire; }
}
