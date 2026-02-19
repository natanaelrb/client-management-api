package com.natan.clientmanagementapi.api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.natan.clientmanagementapi.api.dto.UserRequest;
import com.natan.clientmanagementapi.api.dto.UserResponse;
import com.natan.clientmanagementapi.api.entity.User;
import com.natan.clientmanagementapi.api.exception.DuplicateResourceException;
import com.natan.clientmanagementapi.api.repository.UserRepository;
import com.natan.clientmanagementapi.api.model.Role;
import com.natan.clientmanagementapi.api.dto.UserUpdateRequest;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public UserResponse createUser(UserRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
        throw new DuplicateResourceException("Username já existe");
    }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.valueOf(request.getRole()));
        user.setCreatedAt(LocalDateTime.now());

        User savedUser = userRepository.save(user);

        return new UserResponse(
            savedUser.getId(),
            savedUser.getUsername(),
            savedUser.getRole(),
            savedUser.getCreatedAt()
        );
}

public List<UserResponse> getAllUsers() {
    return userRepository.findAll()
           .stream()
           .map(user -> new UserResponse(
            user.getId(),
            user.getUsername(),
            user.getRole(),
            user.getCreatedAt()
        ))
        .collect(Collectors.toList());
    }

public UserResponse update(Long id, UserUpdateRequest request) {
    User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    
    // Verifica se o novo username já existe e pertence a outro usuário
    if (!user.getUsername().equals(request.getUsername()) && 
        userRepository.existsByUsername(request.getUsername())) {
        throw new DuplicateResourceException("Username já existe");
    }

    user.setUsername(request.getUsername());

    // Só atualiza a senha se vier preenchida
    if (request.getPassword() != null && !request.getPassword().isBlank()) {

    user.setPassword(passwordEncoder.encode(request.getPassword()));

    }

    User updatedUser = userRepository.save(user);

        return new UserResponse(
                updatedUser.getId(),
                updatedUser.getUsername(),
                updatedUser.getRole(),
                updatedUser.getCreatedAt()
        );

    }

}
