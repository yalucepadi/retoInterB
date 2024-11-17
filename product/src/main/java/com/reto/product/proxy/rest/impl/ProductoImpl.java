package com.reto.product.proxy.rest.impl;

import com.reto.product.model.request.ProductoDto;
import com.reto.product.proxy.rest.ProductoService;
import com.reto.product.repository.ProductoRepository;
import com.reto.product.utils.ProductoMapper;
import com.reto.product.utils.ProductoValidador;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductoImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    private final ProductoMapper productoMapper;

    private final ProductoValidador productoValidador;


    public ProductoImpl(ProductoRepository productoRepository, ProductoMapper productoMapper, ProductoValidador productoValidador) {
        this.productoRepository = productoRepository;
        this.productoMapper = productoMapper;

        this.productoValidador = productoValidador;
    }


    @Override
    public Mono<ProductoDto> crearProducto(ProductoDto productoDto) {

        return Mono.just(productoDto)
                .map(productoMapper::toProducto)
                .map(productoRepository::save)
                .map(productoMapper::toDto);
    }

    @Override
    public Mono<ProductoDto> obtenerProductoPorId(Integer id) {
        return Mono.fromCallable(() -> productoRepository.findById(id))
                .flatMap(optionalProducto -> optionalProducto
                        .map(producto -> Mono.just(productoMapper.toDto(producto)))
                        .orElseGet(() -> Mono.error(new RuntimeException("Producto no encontrado")))
                );
    }


    @Override
    public Flux<ProductoDto> obtenerTodosLosProductos() {
        return Flux.fromIterable(productoRepository.findAll())
                .map(productoMapper::toDto);
    }


    @Override
    public Mono<ProductoDto> actualizarProducto(Integer id, ProductoDto productoDto) {

        productoDto.setId(id);

        return Mono.justOrEmpty(productoRepository.findById(id))
                .filter(productoValidador::esProductoValido)
                .map(productoExistente -> {

                    productoMapper.updateProductoFromDto(productoDto, productoExistente);
                    return productoRepository.save(productoExistente);
                })
                .map(productoMapper::toDto)
                .switchIfEmpty(Mono.error(new RuntimeException("Producto no encontrado o inválido")));
    }


}
