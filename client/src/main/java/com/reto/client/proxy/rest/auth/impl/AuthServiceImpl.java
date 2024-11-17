package com.reto.client.proxy.rest.auth.impl;

import com.reto.client.model.request.AuthDto;
import com.reto.client.model.request.AuthRequest;
import com.reto.client.model.request.ClientRequest;
import com.reto.client.proxy.rest.auth.AuthService;
import com.reto.client.repository.AuthRepository;
import com.reto.client.repository.ClienteRepository;
import com.reto.client.utils.AuhtMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class AuthServiceImpl implements AuthService {
    private final AuthRepository authRepository;
    private final ClienteRepository clienteRepository;
    private final AuhtMapper authMapper;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(AuthRepository authRepository, ClienteRepository clienteRepository, AuhtMapper authMapper, PasswordEncoder passwordEncoder) {
        this.authRepository = authRepository;
        this.clienteRepository = clienteRepository;
        this.authMapper = authMapper;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Mono<AuthDto> crearAuth(AuthDto authDto) {
        return Mono.fromCallable(() -> {
           
            ClientRequest cliente = clienteRepository.findById(authDto.getClienteId())
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

           
            AuthRequest auth = authMapper.toAuth(authDto);
            auth.setCliente(cliente);

            authMapper.setPassword(auth, passwordEncoder);


            AuthRequest savedAuth = authRepository.save(auth);


            AuthDto responseDto = authMapper.toDto(savedAuth);


            responseDto.setUser(cliente.getNombres());

            return responseDto;
        }).subscribeOn(Schedulers.boundedElastic());
    }

}



