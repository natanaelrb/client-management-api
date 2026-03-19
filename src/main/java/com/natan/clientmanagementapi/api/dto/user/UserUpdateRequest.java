package com.natan.clientmanagementapi.api.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class UserUpdateRequest {
   
    @Schema(description = "Nome do usuário", example = "Natan Rodrigues")
    @NotBlank
    private String username;

    @Schema(description = "Senha do usuário", example = "123456")
    @NotBlank
    private String password;
    
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
}

