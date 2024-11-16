package com.reto.client.proxy.rest.auth;

import com.reto.client.model.request.AuthDto;
import com.reto.client.model.request.ClientDto;
import reactor.core.publisher.Mono;

public interface AuthService {
    Mono<AuthDto> crearAuth(AuthDto authDto);

}
