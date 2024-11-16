package com.reto.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication

@EntityScan(basePackages = {
		"com.reto.client.model.request",
		"com.reto.client.model.proxy.model",
		"com.reto.client.proxy.product.repository",
		"com.reto.client.model" ,
		"com.reto.client.config",
		"com.reto.client.utils"
		// Agrega el paquete correcto donde están tus entidades
})
@EnableJpaRepositories(basePackages = {
		"com.reto.client.repository",
		"com.reto.client.proxy.product.repository"
})

public class ClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}

}
