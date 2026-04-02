package com.natan.clientmanagementapi.api.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.natan.clientmanagementapi.api.domain.enums.Role;
import com.natan.clientmanagementapi.api.domain.model.User;
import com.natan.clientmanagementapi.api.repository.UserRepository;


import java.time.LocalDateTime;

@Configuration
public class AdminInitializer {

    @Bean
    CommandLineRunner createAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {

            if (userRepository.findByUsername("admin").isEmpty()) {

                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole(Role.ADMIN);
                admin.setCreatedAt(LocalDateTime.now());

                userRepository.save(admin);

                System.out.println("ADMIN criado automaticamente.");
            }
        };
    }
}