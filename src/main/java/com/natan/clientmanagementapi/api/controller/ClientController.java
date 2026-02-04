package com.natan.clientmanagementapi.api.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.natan.clientmanagementapi.api.dto.ClientRequest;
import com.natan.clientmanagementapi.api.dto.ClientResponse;
import com.natan.clientmanagementapi.api.service.ClientService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;


@RestController
@RequestMapping("/api/clients")
@Validated
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<ClientResponse> createClient( @Valid
        @RequestBody ClientRequest request) {

        ClientResponse response = clientService.createClient(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public List<ClientResponse> getAllClients() {
    return clientService.findAll();
    }

    @GetMapping("/{id}")
    public ClientResponse getById(@PathVariable @Positive(message = "Id deve ser positivo") Long id) {
        return clientService.findById(id);
    }

    @PutMapping("/{id}")
    public ClientResponse update(
        @PathVariable @Positive(message = "Id deve ser positivo") Long id,
        @Valid @RequestBody ClientRequest request) {
    return clientService.updateClient(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Positive(message = "Id deve ser positivo") Long id) {
        clientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}