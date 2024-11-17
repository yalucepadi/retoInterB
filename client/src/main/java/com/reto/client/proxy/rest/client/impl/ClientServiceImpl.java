package com.reto.client.proxy.rest.client.impl;

import com.reto.client.model.request.ClientDto;
import com.reto.client.proxy.product.impl.ProductServiceImpl;
import com.reto.client.proxy.product.repository.ProductoRepository;
import com.reto.client.proxy.rest.client.ClientService;
import com.reto.client.repository.ClienteRepository;
import com.reto.client.utils.ClientMapper;
import com.reto.client.utils.ClientValidador;
import com.reto.client.utils.ProductoMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClienteRepository clienteRepository;
    private final ProductoRepository productRepository;

    private final ClientMapper clientMapper;
    private final ProductoMapper productoMapper;

    private final ClientValidador clientValidador;

    private final ProductServiceImpl productService;

    public ClientServiceImpl(ClienteRepository clienteRepository, ProductoRepository productoRepository,
                             ClientMapper clientMapper, ProductoMapper productoMapper, ClientValidador clientValidador,
                             ProductServiceImpl productService) {
        this.clienteRepository = clienteRepository;
        this.productRepository = productoRepository;
        this.clientMapper = clientMapper;
        this.productoMapper = productoMapper;
        this.clientValidador = clientValidador;
        this.productService = productService;
    }


    @Override
    public Mono<ClientDto> crearCliente(ClientDto clientDto, Integer idProducto) {
        return Mono.just(clientDto)
                .map(clientMapper::toClient)  // Paso 1: Convertir DTO a entidad Cliente
                .flatMap(cliente -> {

                    return productService.obtenerProductoPorId(idProducto)
                            .flatMap(productos -> {
                                if (productos != null && !productos.isEmpty()) {

                                    productos.forEach(producto -> producto.setCliente(cliente));


                                    return Flux.fromIterable(productos)
                                            .flatMap(producto -> {

                                                return productService.actualizarClienteIdProducto(idProducto, cliente.getId());
                                            })
                                            .then(Mono.just(cliente));
                                } else {

                                    cliente.setProductosFinancieros(new ArrayList<>());
                                    return Mono.just(cliente);
                                }
                            });
                })
                .flatMap(cliente -> {

                    return Mono.fromCallable(() -> clienteRepository.save(cliente))
                            .map(savedCliente -> clientMapper.toDto(savedCliente));
                });
    }


    @Transactional
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
