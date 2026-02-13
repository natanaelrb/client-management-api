package com.natan.clientmanagementapi.api.security;

import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private final JwtProperties properties;

    public JwtService(JwtProperties properties) {
        this.properties = properties;
    }

    public String generateToken(String subject, String role) {
        return Jwts.builder()
                .claim("role", role)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + properties.getExpiration()))
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(properties.getSecret())))
                .compact();
    }

    public String generateToken(UserDetails user) {
        return Jwts.builder()
                .claim("role", user.getAuthorities().iterator().next().getAuthority())
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + properties.getExpiration()))
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(properties.getSecret())))
                .compact();
    }

    public String extractUsername(String token) {
    return Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(properties.getSecret())))
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }

    public String extractRole(String token) {
    return Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(properties.getSecret())))
            .build()
            .parseClaimsJws(token)
            .getBody()
            .get("role", String.class);
    } 
}
