package com.natan.clientmanagementapi.api.dto;

import java.time.LocalDateTime;

import com.natan.clientmanagementapi.api.entity.Client;

public class ClientResponse {

    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private LocalDateTime createdAt;
    
    public ClientResponse(Long id, String name, String email, String phoneNumber, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.createdAt = createdAt;
    }

    public static ClientResponse fromEntity(Client client) {
        return new ClientResponse(
            client.getId(),
            client.getName(),
            client.getEmail(),
            client.getPhoneNumber(),
            client.getCreatedAt()
        );
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}