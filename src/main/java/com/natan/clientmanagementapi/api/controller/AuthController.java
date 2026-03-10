package com.natan.clientmanagementapi.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.natan.clientmanagementapi.api.dto.auth.LoginRequest;
import com.natan.clientmanagementapi.api.dto.auth.LoginResponse;
import com.natan.clientmanagementapi.api.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid LoginRequest request) {
        authService.register(request);
        return ResponseEntity.ok().build();
    }
    
}
