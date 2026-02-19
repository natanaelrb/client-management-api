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

    public String generateToken(Long userId, String email, String role) {
    return Jwts.builder()
            .claim("userId", userId)
            .claim("role", role)
            .setSubject(email)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + properties.getExpiration()))
            .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(properties.getSecret())))
            .compact();
    }

    public Long extractUserId(String token) {
    return Long.valueOf(
            Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(properties.getSecret())))
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .get("userId").toString()
        );
    
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    
    }

        private boolean isTokenExpired(String token) {
                Date expiration = Jwts.parserBuilder()
                        .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(properties.getSecret())))
                        .build()
                        .parseClaimsJws(token)
                        .getBody()
                        .getExpiration();

    return expiration.before(new Date());
    
    }

}
