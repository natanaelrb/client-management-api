package com.natan.clientmanagementapi.api.service;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.natan.clientmanagementapi.api.domain.model.Client;
import com.natan.clientmanagementapi.api.domain.model.User;
import com.natan.clientmanagementapi.api.dto.client.ClientRequest;
import com.natan.clientmanagementapi.api.dto.client.ClientResponse;
import com.natan.clientmanagementapi.api.exception.DuplicateResourceException;
import com.natan.clientmanagementapi.api.exception.ResourceNotFoundException;
import com.natan.clientmanagementapi.api.repository.ClientRepository;
import com.natan.clientmanagementapi.specification.ClientSpecification;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final UserService userService;

    public ClientService(ClientRepository clientRepository, UserService userService) {
        this.clientRepository = clientRepository;
        this.userService = userService;
    }

    public Page<ClientResponse> getAllClients(Pageable pageable) {
    return clientRepository.findAll(pageable)
            .map(ClientResponse::fromEntity);
    }

    public Page<ClientResponse> searchClients(
        String name,
        String email,
        Pageable pageable
    ) {

    Specification<Client> spec =
            Specification.where(ClientSpecification.nameContains(name))
                         .and(ClientSpecification.emailContains(email));

    Page<Client> clients = clientRepository.findAll(spec, pageable);

    return clients.map(ClientResponse::fromEntity);
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
