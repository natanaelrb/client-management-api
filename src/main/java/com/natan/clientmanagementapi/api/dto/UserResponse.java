package com.natan.clientmanagementapi.api.dto;

import java.time.LocalDateTime;
import com.natan.clientmanagementapi.api.model.Role;
import com.natan.clientmanagementapi.api.entity.User;

public class UserResponse {

    private Long id;
    private String username;
    private Role role;
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

