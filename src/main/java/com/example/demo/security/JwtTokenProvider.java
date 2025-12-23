package com.example.demo.security;

import com.example.demo.model.UserAccount;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt.secret:mySecretKey123456789012345678901234567890}")
    private String jwtSecret;

    @Value("${app.jwt.expiration:86400000}")
    private long jwtExpirationInMs;

    public String generateToken(UserAccount user) {
        // Simple token generation without JWT library
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
        return "USER"; // Default role
    }
}
