package com.natan.clientmanagementapi.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import com.natan.clientmanagementapi.api.domain.model.Client;
import com.natan.clientmanagementapi.api.domain.model.User;
import com.natan.clientmanagementapi.api.dto.client.ClientRequest;
import com.natan.clientmanagementapi.api.repository.ClientRepository;
import com.natan.clientmanagementapi.api.service.ClientService;
import com.natan.clientmanagementapi.api.service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private ClientService clientService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnAllClients() {

        Client client = new Client();
        client.setId(1L);
        client.setName("Empresa Teste");

        Pageable pageable = PageRequest.of(0, 10);

        var page = new org.springframework.data.domain.PageImpl<>(List.of(client));

        when(clientRepository.findAll(pageable)).thenReturn(page);

        var result = clientService.getAllClients(pageable);

        assertEquals(1, result.getContent().size());

        verify(clientRepository).findAll(pageable);
    }

    @Test
    void shouldCreateClient() {

        ClientRequest request = new ClientRequest();
        request.setName("Empresa X");
        request.setEmail("empresa@email.com");
        request.setPhoneNumber("99999999");

        when(clientRepository.existsByEmail(request.getEmail())).thenReturn(false);
        when(clientRepository.existsByPhoneNumber(request.getPhoneNumber())).thenReturn(false);

        User user = new User();
        user.setId(1L);

        when(userService.getAuthenticatedUser()).thenReturn(user);

        Client savedClient = new Client();
        savedClient.setId(1L);
        savedClient.setName("Empresa X");
        savedClient.setEmail("empresa@email.com");
        savedClient.setPhoneNumber("99999999");
        savedClient.setUser(user);

        when(clientRepository.save(any(Client.class))).thenReturn(savedClient);

        var response = clientService.createClient(request);

        assertEquals("Empresa X", response.getName());

        verify(clientRepository).save(any(Client.class));
    }
}