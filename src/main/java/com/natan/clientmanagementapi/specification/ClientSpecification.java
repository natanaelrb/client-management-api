package com.natan.clientmanagementapi.specification;

import org.springframework.data.jpa.domain.Specification;

import com.natan.clientmanagementapi.api.domain.model.Client;

public class ClientSpecification {

    public static Specification<Client> nameContains(String name) {
        return (root, query, cb) ->
                name == null ? null :
                cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Client> emailContains(String email) {
        return (root, query, cb) ->
                email == null ? null :
                cb.like(cb.lower(root.get("email")), "%" + email.toLowerCase() + "%");
    }
}