package com.natan.clientmanagementapi.api.service;

import java.time.LocalDateTime;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.natan.clientmanagementapi.api.domain.enums.Role;
import com.natan.clientmanagementapi.api.domain.model.User;
import com.natan.clientmanagementapi.api.dto.auth.LoginRequest;
import com.natan.clientmanagementapi.api.dto.auth.LoginResponse;
import com.natan.clientmanagementapi.api.repository.UserRepository;
import com.natan.clientmanagementapi.api.security.CustomUserPrincipal;
import com.natan.clientmanagementapi.api.security.JwtService;

@Service
public class AuthService {
    
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(
            AuthenticationManager authenticationManager,
            JwtService jwtService,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        }

    public LoginResponse login(LoginRequest request) {

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

        return new LoginResponse(token);

        }

    public void register(LoginRequest request) {

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);
        user.setCreatedAt(LocalDateTime.now());

    userRepository.save(user);
        }
}