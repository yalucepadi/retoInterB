package com.reto.client.config;

import com.reto.client.model.request.AuthRequest;
import com.reto.client.repository.AuthRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthRequestUserDetailsService implements UserDetailsService {
    private final AuthRepository repository;

    public AuthRequestUserDetailsService(AuthRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthRequest authRequest = repository.findByUser(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return new AuthRequestUserDetails(authRequest);
    }
}
