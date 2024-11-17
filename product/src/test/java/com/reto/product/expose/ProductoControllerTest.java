package com.reto.product.expose;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reto.product.model.request.ProductoDto;
import com.reto.product.model.request.ProductoRequest;
import com.reto.product.model.response.ResponseGeneralDto;
import com.reto.product.proxy.rest.impl.ProductoImpl;
import com.reto.product.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class ProductoControllerTest {

    @Mock
    private ProductoImpl productoService;

    @Mock
    private ProductoRepository productRepository;

    @InjectMocks
    private ProductoController productoController;

    private WebTestClient webTestClient;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        webTestClient = WebTestClient.bindToController(productoController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void crearProducto_Success() {
        // Arrange
        ProductoDto productoDto = new ProductoDto();
        productoDto.setId(1);
        productoDto.setTipoProducto("producto1");
        productoDto.setNombre("Test Product");
        productoDto.setSaldo(0.0);

        ProductoRequest producto = new ProductoRequest();
        producto.setId(1);
        producto.setTipoProducto("producto1");
        producto.setNombre("Test Product");
        producto.setSaldo(0.0);

        when(productoService.crearProducto(any(ProductoDto.class)))
                .thenReturn(Mono.just(productoDto));

        // Act & Assert
        webTestClient.post()
                .uri("/api/product/create")
                .bodyValue(productoDto)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ResponseGeneralDto.class)
                .value(response -> {
                    assert "Created".equals(response.getCode()) :
                            "Expected code to be 'Created' but was '" + response.getCode() + "'";
                    assert response.getStatus() == 201 :
                            "Expected status to be 201 but was " + response.getStatus();
                    assert "Producto creado exitosamente".equals(response.getComment()) :
                            "Expected comment to be 'Producto creado exitosamente' but was '" + response.getComment() + "'";

                    // Verificar que la data no sea null
                    assert response.getData() != null : "Response data should not be null";

                    // Convertir el objeto data a Map para poder acceder a su contenido
                    ObjectMapper objectMapper = new ObjectMapper();
                    @SuppressWarnings("unchecked")
                    Map<String, Object> dataMap = objectMapper.convertValue(response.getData(), Map.class);

                    // Verificar que el map contiene la clave 'message' con el valor esperado
                    assert dataMap.containsKey("message") : "Response data should contain 'message' key";
                    assert ("Producto:Test Product").equals(dataMap.get("message")) :
                            "Expected message to be 'Producto:Test Product' but was '" + dataMap.get("message") + "'";
                });
    }


    @Test
    void obtenerProductoPorId_Success() {
        // Arrange
        Integer id = 1;
        ProductoDto producto = new ProductoDto();
        producto.setId(id);
        producto.setTipoProducto("producto1");
        producto.setNombre("Test Product");
        producto.setSaldo(0.0);

        when(productoService.obtenerProductoPorId(anyInt()))
                .thenReturn(Mono.just(producto));

        // Act & Assert
        webTestClient.get()
                .uri("/api/product/" + id)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ResponseGeneralDto.class)
                .value(response -> {
                    assert response.getStatus() == HttpStatus.OK.value() :
                            "Expected status to be " + HttpStatus.OK.value() + " but was " + response.getStatus();
                    assert "OK".equals(response.getCode()) :
                            "Expected code to be 'OK' but was '" + response.getCode() + "'";
                    assert "Producto encontrado".equals(response.getComment()) :
                            "Expected comment to be 'Producto encontrado' but was '" + response.getComment() + "'";

                    // Verificar que la data no sea null
                    assert response.getData() != null : "Response data should not be null";

                    // Convertir el objeto data a Map para poder acceder a su contenido
                    @SuppressWarnings("unchecked")
                    Map<String, Object> dataMap = objectMapper.convertValue(response.getData(), Map.class);

                    assert dataMap.get("id").equals(id) :
                            "Expected id to be " + id + " but was " + dataMap.get("id");
                    assert dataMap.get("nombre").equals("Test Product") :
                            "Expected nombre to be 'Test Product' but was '" + dataMap.get("nombre") + "'";
                });
    }

    @Test
    void obtenerTodosLosProductos_Success() {
        // Arrange
        ProductoDto producto1 = new ProductoDto();
        producto1.setId(1);
        producto1.setTipoProducto("producto1");
        producto1.setNombre("Test Product 1");
        producto1.setSaldo(0.0);

        ProductoDto producto2 = new ProductoDto();
        producto2.setId(2);
        producto2.setTipoProducto("producto2");
        producto2.setNombre("Test Product 2");
        producto2.setSaldo(0.0);

        when(productoService.obtenerTodosLosProductos())
                .thenReturn(Flux.just(producto1, producto2));

        // Act & Assert
        webTestClient.get()
                .uri("/api/product")
                .exchange()
                .expectStatus().isOk()
                .expectBody(ResponseGeneralDto.class)
                .value(response -> {
                    assert response.getStatus() == HttpStatus.OK.value() :
                            "Expected status to be " + HttpStatus.OK.value() + " but was " + response.getStatus();
                    assert "OK".equals(response.getCode()) :
                            "Expected code to be 'OK' but was '" + response.getCode() + "'";
                    assert "Lista de productos obtenida".equals(response.getComment()) :
                            "Expected comment to be 'Lista de productos obtenida' but was '" + response.getComment() + "'";

                    // Verificar que la data no sea null y sea una lista
                    assert response.getData() != null : "Response data should not be null";

                    @SuppressWarnings("unchecked")
                    List<Map<String, Object>> dataList = objectMapper.convertValue(response.getData(), List.class);
                    assert dataList.size() == 2 : "Expected 2 products but got " + dataList.size();
                });
    }


    @Test
    void actualizarProducto_Success() {
        // Arrange
        Integer id = 1;
        ProductoDto productoDto = new ProductoDto();
        productoDto.setId(id);
        productoDto.setTipoProducto("producto1");
        productoDto.setNombre("Updated Product");
        productoDto.setSaldo(0.0);

        ProductoDto productoActualizado = new ProductoDto();
        productoActualizado.setId(id);
        productoActualizado.setTipoProducto("producto1");
        productoActualizado.setNombre("Updated Product");
        productoActualizado.setSaldo(0.0);

        when(productoService.actualizarProducto(anyInt(), any(ProductoDto.class)))
                .thenReturn(Mono.just(productoActualizado));

        // Act & Assert
        webTestClient.put()
                .uri("/api/product/update/" + id)
                .bodyValue(productoDto)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ResponseGeneralDto.class)
                .value(response -> {
                    assert response.getStatus() == HttpStatus.OK.value() :
                            "Expected status to be " + HttpStatus.OK.value() + " but was " + response.getStatus();
                    assert "OK".equals(response.getCode()) :
                            "Expected code to be 'OK' but was '" + response.getCode() + "'";
                    assert "Producto actualizado correctamente".equals(response.getComment()) :
                            "Expected comment to be 'Producto actualizado correctamente' but was '" + response.getComment() + "'";

                    // Verificar que la data no sea null
                    assert response.getData() != null : "Response data should not be null";

                    // Convertir el objeto data a Map para poder acceder a su contenido
                    @SuppressWarnings("unchecked")
                    Map<String, Object> dataMap = objectMapper.convertValue(response.getData(), Map.class);

                    assert dataMap.get("id").equals(id) :
                            "Expected id to be " + id + " but was " + dataMap.get("id");
                    assert dataMap.get("nombre").equals("Updated Product") :
                            "Expected nombre to be 'Updated Product' but was '" + dataMap.get("nombre") + "'";
                });
    }
}