package com.example.userservice.repository;

import com.example.userservice.entities.Useer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Useer, Long> {

    Useer findByEmail(String email);
}
