package com.natan.clientmanagementapi.api.dto.login;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
    
    @Schema(description = "Nome do usuário", example = "admin")
    @NotBlank
    private String username;

    @Schema(description = "Senha do usuário", example = "admin123")
    @NotBlank
    private String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
