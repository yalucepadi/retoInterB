package com.reto.client.proxy.product.impl;

import com.reto.client.model.proxy.model.request.Product;
import com.reto.client.model.proxy.model.response.ProductResponse;
import com.reto.client.model.request.ClientRequest;
import com.reto.client.proxy.product.ProductService;
import com.reto.client.proxy.product.repository.ProductoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl  implements ProductService {
    private final WebClient webClient;

    private final ProductoRepository productoRepository;

   public  ProductServiceImpl(WebClient.Builder webClientBuilder, ProductoRepository productoRepository){

       this.webClient = webClientBuilder.baseUrl("http://localhost:8080/api/product").build();


       this.productoRepository = productoRepository;
   }




    @Override
    public Mono<List<Product>> obtenerProductoPorId(Integer id) {
        return webClient.get()
                .uri("/{id}", id)  // Usamos el ID correctamente en la URI
                .retrieve()
                .bodyToMono(ProductResponse.class)  // Deserializar en ProductResponse
                .flatMap(productResponse -> {
                    // Verificamos si `productResponse.getData()` tiene un producto
                    if (productResponse != null && productResponse.getData() != null) {
                        // Retornamos una lista con un solo producto
                        return Mono.just(Collections.singletonList(productResponse.getData()));
                    } else {
                        // Si no hay datos, devolvemos una lista vacía
                        return Mono.just(Collections.emptyList());
                    }
                });
    }


    public Mono<Void> actualizarClienteIdProducto(Integer productId, Integer clientId) {
        return Mono.defer(() -> {
            // Realiza la actualización de forma síncrona
            int rowsUpdated = productoRepository.actualizarClienteIdPorProductoId(productId, clientId);

            if (rowsUpdated > 0) {
                // Si se actualizó el producto, retornamos Mono.empty(), que es Mono<Void>
                return Mono.empty();  // Este es Mono<Void>, el tipo esperado
            } else {
                // Si no se encontró el producto, lanzamos una excepción
                return Mono.error(new RuntimeException("No se encontró el producto con id " + productId));
            }
        }).onErrorMap(e -> new RuntimeException("Error al actualizar cliente en producto: " + e.getMessage())).then();
    }

}
