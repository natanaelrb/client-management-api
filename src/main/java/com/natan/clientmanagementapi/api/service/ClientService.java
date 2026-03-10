package com.natan.clientmanagementapi.api.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.natan.clientmanagementapi.api.domain.model.Client;
import com.natan.clientmanagementapi.api.domain.model.User;
import com.natan.clientmanagementapi.api.dto.client.ClientRequest;
import com.natan.clientmanagementapi.api.dto.client.ClientResponse;
import com.natan.clientmanagementapi.api.exception.DuplicateResourceException;
import com.natan.clientmanagementapi.api.exception.ResourceNotFoundException;
import com.natan.clientmanagementapi.api.repository.ClientRepository;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final UserService userService;

    public ClientService(ClientRepository clientRepository, UserService userService) {
        this.clientRepository = clientRepository;
        this.userService = userService;
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

        User user = userService.getAuthenticatedUser();

        Client client = new Client();
        client.setName(request.getName());
        client.setEmail(request.getEmail());
        client.setPhoneNumber(request.getPhoneNumber());
        client.setCreatedAt(LocalDateTime.now());
        client.setUser(user);

        Client savedClient = clientRepository.save(client);

        return ClientResponse.fromEntity(savedClient);
    }

    public ClientResponse updateClient(Long id, ClientRequest request) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));

        // valida email duplicado (exceto o próprio cliente)
        if (!client.getEmail().equals(request.getEmail()) &&
                clientRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email já cadastrado");
        }

        // valida telefone duplicado (exceto o próprio cliente)
        if (!client.getPhoneNumber().equals(request.getPhoneNumber()) &&
                clientRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new DuplicateResourceException("Telefone já cadastrado");
        }

        client.setName(request.getName());
        client.setEmail(request.getEmail());
        client.setPhoneNumber(request.getPhoneNumber());
        client.setCreatedAt(LocalDateTime.now());
       
        Client updatedClient = clientRepository.save(client);

        return ClientResponse.fromEntity(updatedClient);
    }

    public void deleteById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
        clientRepository.delete(client);
    }
}
