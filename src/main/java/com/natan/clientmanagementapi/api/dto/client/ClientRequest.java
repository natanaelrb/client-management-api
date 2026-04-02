package com.natan.clientmanagementapi.api.dto.client;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ClientRequest {

    @Schema(description = "Nome do cliente", example = "João Silva")
    @NotBlank(message = "Nome é obrigatório")
    private String name;

    @Schema(description = "Email do cliente", example = "joao.silva@example.com")
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    private String email;

    @Schema(description = "Telefone do cliente", example = "89999999999")
    @NotBlank(message = "Número de telefone é obrigatório")
    private String phoneNumber;

    public ClientRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
