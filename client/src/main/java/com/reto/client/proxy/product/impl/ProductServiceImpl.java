package com.reto.client.proxy.product.impl;

import com.reto.client.model.proxy.model.ProductDto;
import com.reto.client.proxy.product.ProductService;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class ProductServiceImpl  implements ProductService {
    private final WebClient webClient;

   public  ProductServiceImpl(WebClient.Builder webClientBuilder){

       this.webClient = webClientBuilder.baseUrl("http://localhost:8081/api/product").build();


   }


    @Override
    public Mono<ProductDto> obtenerProductoPorId(Integer id) {
        return webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(ProductDto.class);
    }
}
