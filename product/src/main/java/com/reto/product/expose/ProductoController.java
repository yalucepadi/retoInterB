package com.reto.product.expose;

import com.reto.product.model.request.ProductoDto;
import com.reto.product.model.response.ProductoResponse;
import com.reto.product.model.response.ResponseGeneralDto;
import com.reto.product.proxy.rest.impl.ProductoImpl;
import com.reto.product.repository.ProductoRepository;
import com.reto.product.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductoController {
    private final ProductoImpl productoService;
    private final ProductoRepository productRepository;

    @PostMapping("/create")
    public Mono<ResponseEntity<ResponseGeneralDto>> crearProducto(@RequestBody ProductoDto productoDto) {
        return productoService.crearProducto(productoDto)
                .map(producto -> {


                    return ResponseEntity.ok(
                            ResponseGeneralDto.builder()
                                    .code(Constants.messageProcessCreate)
                                    .status(Constants.HTTP_201_code)
                                    .comment("Producto creado exitosamente")
                                    .data(ProductoResponse.builder()
                                            .message("Producto:" + productoDto.getNombre())
                                            .build())
                                    .build()
                    );
                })
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                        ResponseGeneralDto.builder()
                                .code(Constants.HTTP_500)
                                .status(Constants.HTTP_500_code)
                                .comment("No se pudo crear el producto")
                                .data(null)
                                .build()
                ));
    }


    @GetMapping("/{id}")
    public Mono<ResponseEntity<ResponseGeneralDto>> obtenerProductoPorId(@PathVariable Integer id) {
        return productoService.obtenerProductoPorId(id)
                .map(producto -> ResponseEntity.ok(
                        new ResponseGeneralDto("OK", HttpStatus.OK.value(), "Producto encontrado", producto))
                )
                .onErrorResume(e -> Mono.just(
                        ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(new ResponseGeneralDto("NOT_FOUND", HttpStatus.NOT_FOUND.value(), e.getMessage(), null))
                ));
    }

    @GetMapping
    public Mono<ResponseEntity<ResponseGeneralDto>> obtenerTodosLosProductos() {
        return productoService.obtenerTodosLosProductos()
                .collectList()
                .map(productos -> ResponseEntity.ok(
                        new ResponseGeneralDto("OK", HttpStatus.OK.value(), "Lista de productos obtenida", productos))
                )
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .body(new ResponseGeneralDto("NO_CONTENT", HttpStatus.NO_CONTENT.value(), "No hay productos disponibles", null))
                );
    }

    @PutMapping("/update/{id}")
    public Mono<ResponseEntity<ResponseGeneralDto>> actualizarProducto(@PathVariable Integer id, @RequestBody ProductoDto productoDto) {
        return productoService.actualizarProducto(id, productoDto)
                .map(productoActualizado -> ResponseEntity.ok(
                        new ResponseGeneralDto("OK", HttpStatus.OK.value(), "Producto actualizado correctamente", productoActualizado))
                )
                .onErrorResume(e -> Mono.just(
                        ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(new ResponseGeneralDto("BAD_REQUEST", HttpStatus.BAD_REQUEST.value(), e.getMessage(), null))
                ));
    }


}

