package com.reto.client.proxy.product;

import com.reto.client.model.proxy.model.ProductDto;
import reactor.core.publisher.Mono;

public interface ProductService {
    Mono<ProductDto> obtenerProductoPorId(Integer id);

}
