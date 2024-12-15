package com.example.userservice.repository;

import com.example.userservice.entities.Admin;
import com.example.userservice.entities.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MedecinRepository extends JpaRepository<Medecin, Long> {

    boolean existsById(Long id);

    List<Medecin> findByLabelContainingIgnoreCase(String label);
    List<Medecin> findByPrenomContainingIgnoreCase(String prenom);
    List<Medecin> findByAdmin(Admin admin);


}
