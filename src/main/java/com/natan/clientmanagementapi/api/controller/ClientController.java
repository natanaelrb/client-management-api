package com.natan.clientmanagementapi.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.natan.clientmanagementapi.api.dto.ClientRequest;
import com.natan.clientmanagementapi.api.dto.ClientResponse;
import com.natan.clientmanagementapi.api.service.ClientService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/clients")
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
}