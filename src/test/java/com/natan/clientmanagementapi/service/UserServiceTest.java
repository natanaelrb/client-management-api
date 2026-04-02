package com.natan.clientmanagementapi.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


import com.natan.clientmanagementapi.api.domain.enums.Role;
import com.natan.clientmanagementapi.api.domain.model.User;
import com.natan.clientmanagementapi.api.dto.user.UserRequest;
import com.natan.clientmanagementapi.api.repository.UserRepository;
import com.natan.clientmanagementapi.api.service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.springframework.security.crypto.password.PasswordEncoder;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateUser() {

        UserRequest request = new UserRequest();
        request.setUsername("natan");
        request.setPassword("123456");
        request.setRole("ADMIN");

        when(userRepository.existsByUsername("natan")).thenReturn(false);
        when(passwordEncoder.encode("123456")).thenReturn("senhaCriptografada");

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setUsername("natan");
        savedUser.setPassword("senhaCriptografada");
        savedUser.setRole(Role.ADMIN);

        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        var response = userService.createUser(request);

        assertEquals("natan", response.getUsername());

        verify(userRepository).save(any(User.class));
    }

}