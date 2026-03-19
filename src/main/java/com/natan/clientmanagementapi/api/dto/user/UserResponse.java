package com.natan.clientmanagementapi.api.dto.user;

import java.time.LocalDateTime;

import com.natan.clientmanagementapi.api.domain.enums.Role;
import com.natan.clientmanagementapi.api.domain.model.User;

import io.swagger.v3.oas.annotations.media.Schema;

public class UserResponse {

    @Schema(description = "ID do usuário", example = "1")
    private Long id;

    @Schema(description = "Nome do usuário", example = "Natan")
    private String username;

    @Schema(description = "Função do usuário", example = "USER")
    private Role role;
    
    @Schema(description = "Data de criação do usuário", example = "2023-01-01T00:00:00")
    private LocalDateTime createdAt;

    public UserResponse(Long id, String username, Role role, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.createdAt = createdAt;
    }

    public UserResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.role = user.getRole();
        this.createdAt = user.getCreatedAt();
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Role getRole() {
        return role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}

