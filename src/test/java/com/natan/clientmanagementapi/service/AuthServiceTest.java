package com.natan.clientmanagementapi.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.util.List;

import com.natan.clientmanagementapi.api.dto.login.LoginRequest;
import com.natan.clientmanagementapi.api.dto.login.LoginResponse;
import com.natan.clientmanagementapi.api.repository.UserRepository;
import com.natan.clientmanagementapi.api.security.CustomUserPrincipal;
import com.natan.clientmanagementapi.api.security.JwtService;
import com.natan.clientmanagementapi.api.service.AuthService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;


class AuthServiceTest {
    
    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtService jwtService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldLoginSuccessfully() {

        LoginRequest request = new LoginRequest();
        request.setUsername("admin");
        request.setPassword("123456");

        Authentication authentication = mock(Authentication.class);
        CustomUserPrincipal principal = mock(CustomUserPrincipal.class);

        when(principal.getId()).thenReturn(1L);
        when(principal.getUsername()).thenReturn("admin");

        List<GrantedAuthority> authorities =
            List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));

        doReturn(authorities).when(principal).getAuthorities();

        when(authenticationManager.authenticate(any()))
                .thenReturn(authentication);

        when(authentication.getPrincipal())
                .thenReturn(principal);

        when(jwtService.generateToken(anyLong(), anyString(), anyString()))
                .thenReturn("fake-jwt-token");

        LoginResponse response = authService.login(request);

        assertEquals("fake-jwt-token", response.getToken());

        verify(authenticationManager).authenticate(any());
        verify(jwtService).generateToken(anyLong(), anyString(), anyString());
    }
}
