package com.natan.clientmanagementapi.api.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import com.natan.clientmanagementapi.api.repository.UserRepository;
import com.natan.clientmanagementapi.api.security.CustomUserPrincipal;
import com.natan.clientmanagementapi.api.security.JwtService;

@Service
public class AuthService {
    
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(
            AuthenticationManager authenticationManager,
            JwtService jwtService,
            UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public AuthResponse login(AuthRequest request) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getUsername(),
                                request.getPassword()
                        )
                );

        CustomUserPrincipal principal =
                (CustomUserPrincipal) authentication.getPrincipal();

        String token = jwtService.generateToken(
                principal.getId(),
                principal.getUsername(),
                principal.getAuthorities()
                        .iterator().next().getAuthority()
        );

        return new AuthResponse(token);
    }
}