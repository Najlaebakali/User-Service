package com.example.userservice;

import com.example.userservice.configuration.RSAConfig;
import com.example.userservice.dto.AdminDTO;
import com.example.userservice.service.AdminService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableConfigurationProperties(RSAConfig.class)
public class UserServiceApplication {



    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
    @Bean
    PasswordEncoder passwordEncoder (){
        return new BCryptPasswordEncoder();
    };


    // et pour eviter de creer ladmin manuellement.
   /* @Bean
    @Profile("!test")
    CommandLineRunner commandLineRunner(AdminService adminService) {
        return args -> {
            AdminDTO admin = new AdminDTO();
            admin.setId(1L);
            admin.setPrenom("Najlae");
            admin.setNom("Elbakali");
            admin.setEmail("najlaebk@gmail.com");
            admin.setPassword("1234");
            admin.setRole("ADMIN");
            admin.setLabel("label");
            admin = adminService.saveAdmin(admin);
            System.out.println("admin : "+admin);

        };
    }*/

}
