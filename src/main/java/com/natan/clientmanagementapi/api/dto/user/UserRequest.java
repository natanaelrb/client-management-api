package com.natan.clientmanagementapi.api.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class UserRequest {
    
    @Schema(description = "Nome do usuário", example = "Natan")
    @NotBlank
    private String username;

    @Schema(description = "Senha do usuário", example = "123456")
    @NotBlank
    private String password;

    @Schema(description = "Role do usuário", example = "USER")
    private String role;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}

