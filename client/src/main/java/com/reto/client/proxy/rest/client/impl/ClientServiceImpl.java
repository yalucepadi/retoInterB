package com.reto.client.proxy.rest.client.impl;

import com.reto.client.model.request.ClientDto;

import com.reto.client.repository.ClienteRepository;
import com.reto.client.utils.ClientMapper;
import com.reto.client.utils.ClientValidador;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class ClientServiceImpl implements ClientService{
    private final ClienteRepository clienteRepository;

    private final ClientMapper clientMapper;

    private final ClientValidador clientValidador;

    public ClientServiceImpl(ClienteRepository clienteRepository, ClientMapper clientMapper, ClientValidador clientValidador) {
        this.clienteRepository = clienteRepository;
        this.clientMapper = clientMapper;
        this.clientValidador = clientValidador;
    }

    @Override
    public Mono<ClientDto> crearCliente(ClientDto clientDto) {

        return Mono.just(clientDto)
                .map(clientMapper::toClient)
                .map(clienteRepository::save)
                .map(clientMapper::toDto);

    }

    @Override
    public Mono<ClientDto> obtenerClientePorId(Integer id) {
        return Mono.fromCallable(() -> clienteRepository.findById(id))
                .flatMap(optionalClient -> optionalClient
                        .map(client -> Mono.just(clientMapper.toDto(client)))
                        .orElseGet(() -> Mono.error(new RuntimeException("Cliente no encontrado")))
                );



    }

    @Override
    public Flux<ClientDto> obtenerTodosLosClientes() {
        return Flux.fromIterable(clienteRepository.findAll())
                .map(clientMapper::toDto);
    }

    @Override
    public Mono<ClientDto> actualizarCliente(Integer id, ClientDto clientDto) {
        clientDto.setId(id);

        return Mono.justOrEmpty(clienteRepository.findById(id))
                .filter(clientValidador::esClienteValido)
                .map(clienteExistente -> {

                    clientMapper.updateClientFromDto(clientDto, clienteExistente);
                    return clienteRepository.save(clienteExistente);
                })
                .map(clientMapper::toDto)
                .switchIfEmpty(Mono.error(new RuntimeException("Cliente no encontrado o inválido")));



    }
}
