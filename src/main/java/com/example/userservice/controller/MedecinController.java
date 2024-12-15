package com.example.userservice.controller;


import com.example.userservice.dto.MedecinDTO;
import com.example.userservice.service.MedecinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class MedecinController {

    private final MedecinService medecinService;

    @Autowired
    public MedecinController(MedecinService medecinService) {
        this.medecinService = medecinService;
    }

    @QueryMapping
    public List<MedecinDTO> findAllMedecins() {
        return medecinService.findAllMedecins();
    }

    @QueryMapping
    public MedecinDTO findMedecinById(@Argument Long id) {
        return medecinService.findMedecinById(id);
    }

    @MutationMapping
    public MedecinDTO saveMedecin(@Argument MedecinDTO medecin,@Argument Long adminId) {
        return medecinService.saveMedecin(medecin,adminId);
    }

    /*@MutationMapping
    public MedecinDTO updateMedecin(@Argument Long id, @Argument MedecinDTO medecin) {
        return medecinService.updateMedecin(id, medecin);
    }*/

    @MutationMapping
    public Boolean deleteMedecin(@Argument Long id) {
        return medecinService.deleteMedecin(id);
    }

    @QueryMapping
    public List<MedecinDTO> findByLabel(@Argument String label) {
        return medecinService.findByLabelContainingIgnoreCase(label);
    }

    @QueryMapping
    public List<MedecinDTO> findByPrenom(@Argument String prenom) {
        return medecinService.findByPrenomContainingIgnoreCase(prenom);
    }

    @QueryMapping
    public List<MedecinDTO> findByAdmin(@Argument Long id) {
        return medecinService.findByAdmin(id);
    }
}
