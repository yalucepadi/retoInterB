package com.reto.product;


import org.mapstruct.Mapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux

@ComponentScan(basePackages = {
		"com.reto.product.expose",
		"com.reto.product.proxy.rest",
		"com.reto.product.utils"
})
@EntityScan(basePackages = "com.reto.product.model.request")
@EnableJpaRepositories(basePackages = "com.reto.product.repository")



public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

}
