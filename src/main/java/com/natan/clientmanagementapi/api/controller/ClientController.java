package com.natan.clientmanagementapi.api.controller;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.natan.clientmanagementapi.api.dto.client.ClientRequest;
import com.natan.clientmanagementapi.api.dto.client.ClientResponse;
import com.natan.clientmanagementapi.api.service.ClientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@Tag(name = "Clients", description = "Gerenciamento de clientes")
@RestController
@RequestMapping("/clients")
@Validated
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Operation(
        summary = "Listar clientes",
        description = "Retorna uma lista paginada de clientes com suporte a filtros por nome e email"
    )
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Lista de clientes retornada com sucesso"),
    @ApiResponse(responseCode = "403", description = "Acesso negado")
    })

    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public Page<ClientResponse> getAllClients(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @ParameterObject Pageable pageable) {
        return clientService.searchClients(name, email, pageable);
    }

    @Operation(
        summary = "Criar um novo cliente",
        description = "Cria um novo cliente no sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "403", description = "Acesso negado")
    })
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ClientResponse> createClient( @Valid
        @RequestBody ClientRequest request) {

        ClientResponse response = clientService.createClient(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
        summary = "Buscar cliente pelo ID",
        description = "Retorna os detalhes de um cliente específico"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
        @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/{id}")
    public ClientResponse getById(@PathVariable @Positive(message = "Id deve ser positivo") Long id) {
        return clientService.findById(id);
    }

    @Operation(
        summary = "Atualizar cliente",
        description = "Atualiza os dados de um cliente existente"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ClientResponse update(
        @PathVariable @Positive(message = "Id deve ser positivo") Long id,
        @Valid @RequestBody ClientRequest request) {
        return clientService.updateClient(id, request);
    }

    @Operation(
        summary = "Deletar cliente",
        description = "Remove um cliente específico do sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Cliente deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Positive(message = "Id deve ser positivo") Long id) {
        clientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}