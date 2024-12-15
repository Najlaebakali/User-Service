package com.example.userservice.repository;

import com.example.userservice.entities.Medecin;
import com.example.userservice.entities.Secretaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SecretaireRepository extends JpaRepository<Secretaire, Long> {


    List<Secretaire> findByLabelContainingIgnoreCase(String label);
    List<Secretaire> findByPrenomContainingIgnoreCase(String prenom);
    List<Secretaire> findByMedecin(Medecin medecin);

}
