package com.example.userservice;

import com.example.userservice.configuration.RSAConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableConfigurationProperties(RSAConfig.class)
public class UserServiceApplication {

    @Bean
    PasswordEncoder passwordEncoder (){
        return new BCryptPasswordEncoder();
    };

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

}
