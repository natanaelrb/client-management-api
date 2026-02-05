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

        // TEMPORÁRIO (depois troca por banco)
        if (!request.getEmail().equals("admin@email.com") ||
            !request.getPassword().equals("123456")) {
            throw new RuntimeException("Credenciais inválidas");
        }

        String token = jwtService.generateToken(request.getEmail());
        return new AuthResponse(token);
    }
}
