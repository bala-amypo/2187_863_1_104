package com.example.demo.security;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    public String generateToken(String email) {
        return "dummy-jwt-token-" + email;
    }

    public boolean validateToken(String token) {
        return token != null && token.startsWith("dummy-jwt-token");
    }

    public String getEmailFromToken(String token) {
        return token.replace("dummy-jwt-token-", "");
    }
}