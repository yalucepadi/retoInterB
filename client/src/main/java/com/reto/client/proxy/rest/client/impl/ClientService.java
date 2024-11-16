package com.reto.client.proxy.rest.client.impl;

import com.reto.client.model.request.ClientDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientService {

    Mono<ClientDto> crearCliente(ClientDto clientDto,Integer id);
    Mono<ClientDto> obtenerClientePorId(Integer id);


    Flux<ClientDto> obtenerTodosLosClientes();
    Mono<ClientDto> actualizarCliente(Integer id, ClientDto clientDto);
}
