package com.reto.client.proxy.product;

import com.reto.client.model.proxy.model.request.Product;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ProductService {
    Mono<List<Product>> obtenerProductoPorId(Integer id);

}
