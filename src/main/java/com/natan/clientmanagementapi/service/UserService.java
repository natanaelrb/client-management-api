package com.natan.clientmanagementapi.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.natan.clientmanagementapi.dto.UserRequest;
import com.natan.clientmanagementapi.dto.UserResponse;
import com.natan.clientmanagementapi.entity.User;
import com.natan.clientmanagementapi.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse createUser(UserRequest request) {

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
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
}