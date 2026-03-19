package com.natan.clientmanagementapi.api.dto.login;

import io.swagger.v3.oas.annotations.media.Schema;

public class LoginResponse {

    @Schema(description = "Token JWT gerado após login",
            example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String token;

    public LoginResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
    
}
