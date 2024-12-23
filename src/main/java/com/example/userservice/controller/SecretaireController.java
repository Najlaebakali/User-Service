package com.example.userservice.controller;

import com.example.userservice.dto.SecretaireDTO;
import com.example.userservice.service.SecretaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SecretaireController {


    private final SecretaireService secretaireService;

    @Autowired
    public SecretaireController(SecretaireService secretaireService) {
        this.secretaireService = secretaireService;
    }

    @QueryMapping
    public List<SecretaireDTO> findAllSecretaires() {
        return secretaireService.findAllSecretaires();
    }

    @QueryMapping
    public SecretaireDTO findSecretaireById(@Argument Long id) {
        return secretaireService.findSecretaireById(id);
    }

    @MutationMapping
    public SecretaireDTO saveSecretaire(@Argument SecretaireDTO secretaire,@Argument Long medecinId) {
        return secretaireService.saveSecretaire(secretaire,medecinId);
    }

    @MutationMapping
    public SecretaireDTO updateSecretaire(@Argument Long id, @Argument SecretaireDTO secretaire) {
        return secretaireService.updateSecretaire(id, secretaire);
    }



    @MutationMapping
    public Boolean deleteSecretaire(@Argument Long id) {
        return secretaireService.deleteSecretaire(id);
    }

    @QueryMapping
    public List<SecretaireDTO> findByLabelSec(@Argument String label) {
        return secretaireService.findByLabelContainingIgnoreCase(label);
    }

    @QueryMapping
    public List<SecretaireDTO> findByPrenomSec(@Argument String prenom) {
        return secretaireService.findByPrenomContainingIgnoreCase(prenom);
    }

    @QueryMapping
    public List<SecretaireDTO> findByMedecin(@Argument Long id) {
        return secretaireService.findByMedecin(id);
    }

}
