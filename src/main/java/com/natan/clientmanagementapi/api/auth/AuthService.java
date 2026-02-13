package com.natan.clientmanagementapi.api.auth;

import org.springframework.stereotype.Service;

import com.natan.clientmanagementapi.api.security.JwtService;

@Service
public class AuthService {
    
    private final JwtService jwtService;

    public AuthService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public AuthResponse login(AuthRequest request) {

        // ADMIN
        // Temporário (depois trocar por banco)
        if (request.getEmail().equals("admin@email.com") &&
            request.getPassword().equals("123456")) {

            String token = jwtService.generateToken(
                    request.getEmail(),
                    "ROLE_ADMIN"
            );

            return new AuthResponse(token);
        }

        // USER
        if (request.getEmail().equals("user@email.com") &&
            request.getPassword().equals("123456")) {

            String token = jwtService.generateToken(
                    request.getEmail(),
                    "ROLE_USER"
            );

            return new AuthResponse(token);
        }

        throw new RuntimeException("Credenciais inválidas");

    }
}