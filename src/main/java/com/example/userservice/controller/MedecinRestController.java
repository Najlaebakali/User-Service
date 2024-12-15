package com.example.userservice.controller;

import com.example.userservice.service.MedecinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/medecins")
public class MedecinRestController {

    private final MedecinService medecinService;
    @Autowired
    public MedecinRestController(MedecinService medecinService) {
        this.medecinService = medecinService; }
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> medecinExists(@PathVariable Long id) {
        boolean exists = medecinService.medecinExists(id);
        return ResponseEntity.ok(exists);
    }
}
