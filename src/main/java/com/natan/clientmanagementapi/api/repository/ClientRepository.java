package com.natan.clientmanagementapi.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.natan.clientmanagementapi.api.domain.model.Client;
import com.natan.clientmanagementapi.api.domain.model.User;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByEmail(String email);

    Client findByPhoneNumber(String phoneNumber);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    List<Client> findByUser(User user);
}