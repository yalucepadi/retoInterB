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
                    // Paso 2: Obtener los productos asociados al cliente por idProducto
                    return productService.obtenerProductoPorId(idProducto)
                            .flatMap(productos -> {
                                if (productos != null && !productos.isEmpty()) {
                                    // Paso 3: Asignar los productos al cliente y actualizar el clientId si el producto existe
                                    productos.forEach(producto -> producto.setCliente(cliente)); // Establecer cliente en cada producto

                                    // Usamos un Flux para iterar sobre la lista de productos y actualizar cada uno
                                    return Flux.fromIterable(productos)  // Convertimos la lista de productos en un Flux
                                            .flatMap(producto -> {
                                                // Aquí pasamos el id del producto y el id del cliente para actualizar el producto
                                                return productService.actualizarClienteIdProducto(idProducto, cliente.getId());
                                            })
                                            .then(Mono.just(cliente));  // Una vez que todos los productos se actualicen, retornamos el cliente
                                } else {
                                    // Si no hay productos asociados, asignamos una lista vacía
                                    cliente.setProductosFinancieros(new ArrayList<>());
                                    return Mono.just(cliente);  // Si no hay productos, regresamos el cliente tal cual
                                }
                            });
                })
                .flatMap(cliente -> {
                    // Paso 4: Guardar al cliente en el repositorio (JPA)
                    return Mono.fromCallable(() -> clienteRepository.save(cliente))  // Guardamos el cliente
                            .map(savedCliente -> clientMapper.toDto(savedCliente));  // Convertimos el cliente guardado a DTO y lo retornamos
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
