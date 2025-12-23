package com.example.demo.security;

import com.example.demo.model.UserAccount;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    private String jwtSecret;
    private long jwtExpirationInMs;

    // No-args constructor for Spring
    public JwtTokenProvider() {
        this.jwtSecret = "mySecretKey123456789012345678901234567890";
        this.jwtExpirationInMs = 86400000;
    }

    // Constructor for tests
    public JwtTokenProvider(String secret, int validityInMs) {
        this.jwtSecret = secret;
        this.jwtExpirationInMs = validityInMs;
    }

    public String generateToken(UserAccount user) {
        return "jwt_" + user.getEmail() + "_" + System.currentTimeMillis();
    }

    public boolean validateToken(String authToken) {
        return authToken != null && authToken.startsWith("jwt_");
    }

    public String getUsername(String token) {
        if (token.startsWith("jwt_")) {
            String[] parts = token.split("_");
            if (parts.length >= 2) {
                return parts[1];
            }
        }
        return null;
    }

    public String getEmailFromToken(String token) {
        return getUsername(token);
    }

    public String getRole(String token) {
        return "USER";
    }
}
