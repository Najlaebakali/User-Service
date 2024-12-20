package com.example.userservice.controller;


import com.example.userservice.dto.JwtResponse;
import com.example.userservice.dto.LoginRequest;
import com.example.userservice.dto.UserDTO;
import com.example.userservice.dto.AdminDTO;
import com.example.userservice.dto.MedecinDTO;
import com.example.userservice.dto.SecretaireDTO;
import com.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtEncoder jwtEncoder;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtEncoder jwtEncoder, UserService userService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtEncoder = jwtEncoder;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    /*@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            User user = (User) authentication.getPrincipal();
            String token = jwtEncoder.encode(JwtEncoderParameters.from(JwtClaimsSet.builder()
                    .issuer("self")
                    .subject(user.getUsername())
                    .issuedAt(Instant.now())
                    .expiresAt(Instant.now().plus(1, ChronoUnit.HOURS))
                    .claim("role", user.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority)
                            .collect(Collectors.toList()))
                    .build())).getTokenValue();
            return ResponseEntity.ok(new JwtResponse(token));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }*/
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("Login attempt: " + loginRequest.getEmail() + " with password: " + loginRequest.getPassword());
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            User user = (User) authentication.getPrincipal();
            System.out.println("Authentication successful for user: " + user.getUsername());
            String token = jwtEncoder.encode(JwtEncoderParameters.from(JwtClaimsSet.builder()
                    .issuer("self")
                    .subject(user.getUsername())
                    .issuedAt(Instant.now())
                    .expiresAt(Instant.now().plus(1, ChronoUnit.HOURS))
                    .claim("role", user.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority)
                            .collect(Collectors.toList()))
                    .build())).getTokenValue();
            return ResponseEntity.ok(new JwtResponse(token));
        } catch (AuthenticationException e) {
            System.out.println("Login failed for: " + loginRequest.getEmail() + " - " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }


    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        UserDTO savedUser = userService.saveUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PostMapping("/signup/admin")
    public ResponseEntity<?> signupAdmin(@RequestBody AdminDTO adminDTO) {
        adminDTO.setPassword(passwordEncoder.encode(adminDTO.getPassword()));
        AdminDTO savedAdmin = userService.saveAdmin(adminDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAdmin);
    }
    @PostMapping("/signup/medecin")
    public ResponseEntity<?> signupMedecin(@RequestBody MedecinDTO medecinDTO, @RequestParam Long adminId) {
        medecinDTO.setPassword(passwordEncoder.encode(medecinDTO.getPassword()));
        MedecinDTO savedMedecin = userService.saveMedecin(medecinDTO, adminId);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMedecin);
    }
    @PostMapping("/signup/secretaire")
    public ResponseEntity<?> signupSecretaire(@RequestBody SecretaireDTO secretaireDTO, @RequestParam Long medecinId) {
        secretaireDTO.setPassword(passwordEncoder.encode(secretaireDTO.getPassword()));
        SecretaireDTO savedSecretaire = userService.saveSecretaire(secretaireDTO, medecinId);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSecretaire);
    }
}
