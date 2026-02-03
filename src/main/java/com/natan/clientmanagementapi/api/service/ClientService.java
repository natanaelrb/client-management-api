package com.natan.clientmanagementapi.api.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.natan.clientmanagementapi.api.dto.ClientRequest;
import com.natan.clientmanagementapi.api.dto.ClientResponse;
import com.natan.clientmanagementapi.api.entity.Client;
import com.natan.clientmanagementapi.api.exception.DuplicateResourceException;
import com.natan.clientmanagementapi.api.exception.ResourceNotFoundException;
import com.natan.clientmanagementapi.api.repository.ClientRepository;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<ClientResponse> findAll() {
        return clientRepository.findAll()
                .stream()
                .map(ClientResponse::fromEntity)
                .toList();
    }

    public ClientResponse findById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
        return ClientResponse.fromEntity(client);
    }

    public ClientResponse createClient(ClientRequest request) {

        if (clientRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email já cadastrado");
        }

        if (clientRepository.existsByPhoneNumber(request.getPhoneNumber())) {
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
