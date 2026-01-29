package com.natan.clientmanagementapi.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.natan.clientmanagementapi.api.entity.Client;


public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByEmail(String email);

    Client findByPhoneNumber(String phoneNumber);
}