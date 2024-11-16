package com.reto.client.config;

import com.reto.client.model.request.AuthRequest;
import com.reto.client.repository.AuthRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
/*
@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner init(AuthRepository repository, PasswordEncoder passwordEncoder) {
        return args -> {
            AuthRequest authRequest = new AuthRequest();
            authRequest.setUser("admin");
            authRequest.setPassword(passwordEncoder.encode("admin123"));
            repository.save(authRequest);
        };
    }

}

 */
