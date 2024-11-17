package com.reto.product.proxy.rest;


import com.reto.product.model.request.ProductoDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductoService {

    Mono<ProductoDto> crearProducto(ProductoDto productoDto);

    Mono<ProductoDto> obtenerProductoPorId(Integer id);


    Flux<ProductoDto> obtenerTodosLosProductos();

    Mono<ProductoDto> actualizarProducto(Integer id, ProductoDto productoDto);
}
