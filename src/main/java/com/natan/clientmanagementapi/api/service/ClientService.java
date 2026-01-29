package com.natan.clientmanagementapi.api.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.natan.clientmanagementapi.api.dto.ClientRequest;
import com.natan.clientmanagementapi.api.dto.ClientResponse;
import com.natan.clientmanagementapi.api.entity.Client;
import com.natan.clientmanagementapi.api.exception.DuplicateResourceException;
import com.natan.clientmanagementapi.api.repository.ClientRepository;



@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientResponse createClient(ClientRequest request) {

        if (clientRepository.findByEmail(request.getEmail()) != null) {
            throw new DuplicateResourceException("Email já cadastrado");
        }

        if (clientRepository.findByPhoneNumber(request.getPhoneNumber()) != null) {
            throw new DuplicateResourceException("Telefone já cadastrado");
        }

        Client client = new Client();
        client.setName(request.getName());
        client.setEmail(request.getEmail());
        client.setPhoneNumber(request.getPhoneNumber());
        client.setCreatedAt(LocalDateTime.now());

        Client savedClient = clientRepository.save(client);

        return new ClientResponse(
            savedClient.getId(),
            savedClient.getName(),
            savedClient.getEmail(),
            savedClient.getPhoneNumber(),
            savedClient.getCreatedAt()
        );
    }
}
