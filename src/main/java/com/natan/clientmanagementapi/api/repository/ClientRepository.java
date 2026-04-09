package com.natan.clientmanagementapi.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.natan.clientmanagementapi.api.domain.model.Client;
import com.natan.clientmanagementapi.api.domain.model.User;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface ClientRepository extends 
        JpaRepository<Client, Long>,
        JpaSpecificationExecutor<Client> {

    Client findByEmail(String email);

    Client findByPhoneNumber(String phoneNumber);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    List<Client> findByUser(User user);

    Page<Client> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Client> findByNameContainingIgnoreCaseAndEmailContainingIgnoreCase(
        String name,
        String email,
        Pageable pageable
    );
}