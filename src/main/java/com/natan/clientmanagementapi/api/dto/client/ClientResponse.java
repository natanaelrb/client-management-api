package com.natan.clientmanagementapi.api.dto.client;

import java.time.LocalDateTime;

import com.natan.clientmanagementapi.api.domain.model.Client;

import io.swagger.v3.oas.annotations.media.Schema;

public class ClientResponse {

    @Schema(description = "ID do cliente", example = "1")
    private Long id;
    
    @Schema(description = "Nome do cliente", example = "João Silva")
    private String name;

    @Schema(description = "Email do cliente", example = "joao.silva@example.com")
    private String email;

    @Schema(description = "Telefone do cliente", example = "89999999999")
    private String phoneNumber;

    @Schema(description = "Data de criação do cliente", example = "2023-01-01T00:00:00")
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