package com.reto.client.proxy.product.impl;

import com.reto.client.model.proxy.model.request.Product;
import com.reto.client.model.proxy.model.response.ProductResponse;
import com.reto.client.proxy.product.ProductService;
import com.reto.client.proxy.product.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final WebClient webClient;

    private final ProductoRepository productoRepository;

    public ProductServiceImpl(WebClient.Builder webClientBuilder, ProductoRepository productoRepository) {

        this.webClient = webClientBuilder.baseUrl("http://localhost:8080/api/product").build();


        this.productoRepository = productoRepository;
    }


    @Override
    public Mono<List<Product>> obtenerProductoPorId(Integer id) {
        return webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(ProductResponse.class)  // Deserializar en ProductResponse
                .flatMap(productResponse -> {

                    if (productResponse != null && productResponse.getData() != null) {

                        return Mono.just(Collections.singletonList(productResponse.getData()));
                    } else {

                        return Mono.just(Collections.emptyList());
                    }
                });
    }


    public Mono<Void> actualizarClienteIdProducto(Integer productId, Integer clientId) {
        return Mono.defer(() -> {

            int rowsUpdated = productoRepository.actualizarClienteIdPorProductoId(productId, clientId);

            if (rowsUpdated > 0) {

                return Mono.empty();  // Este es Mono<Void>, el tipo esperado
            } else {

                return Mono.error(new RuntimeException("No se encontró el producto con id " + productId));
            }
        }).onErrorMap(e -> new RuntimeException("Error al actualizar cliente en producto: " + e.getMessage()))
                .then();
    }

}
