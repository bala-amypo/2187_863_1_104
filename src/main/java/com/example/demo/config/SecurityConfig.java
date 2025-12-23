package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {

    @Bean
    public SimplePasswordEncoder passwordEncoder() {
        return new SimplePasswordEncoder();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static class SimplePasswordEncoder {
        public String encode(String password) {
            return "encoded_" + password;
        }
        
        public boolean matches(String rawPassword, String encodedPassword) {
            return encodedPassword.equals("encoded_" + rawPassword);
        }
    }

    public static class BCryptPasswordEncoder {
        public String encode(String password) {
            return "bcrypt_" + password;
        }
        
        public boolean matches(String rawPassword, String encodedPassword) {
            return encodedPassword.equals("bcrypt_" + rawPassword);
        }
    }
}