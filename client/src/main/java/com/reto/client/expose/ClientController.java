package com.reto.client.expose;

import com.reto.client.model.request.ClientDto;
import com.reto.client.model.response.ClienteResponse;
import com.reto.client.model.response.ResponseGeneralDto;
import com.reto.client.proxy.rest.client.impl.ClientServiceImpl;
import com.reto.client.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientServiceImpl clientService;

    @PostMapping("/create")
    public Mono<ResponseEntity<ResponseGeneralDto>> crearCliente(@RequestBody ClientDto clientDto, @RequestParam Integer id) {
        return clientService.crearCliente(clientDto,id)
                .map(client -> {




                    return ResponseEntity.ok(
                            ResponseGeneralDto.builder()
                                    .code(Constants.messageProcessCreate)
                                    .status(Constants.HTTP_201_code)
                                    .comment("Cliente creado exitosamente")
                                    .data(ClienteResponse.builder()
                                            .message("Cliente:"+"Nombre:"+clientDto.getNombres()+" "+
                                                    "Apellido:"+clientDto.getApellidos())
                                            .build())
                                    .build()
                    );
                })
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                        ResponseGeneralDto.builder()
                                .code(Constants.HTTP_500)
                                .status(Constants.HTTP_500_code)
                                .comment("No se pudo crear el Cliente")
                                .data(null)
                                .build()
                ));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<ResponseGeneralDto>> obtenerProductoPorId(@PathVariable Integer id) {
        return clientService.obtenerClientePorId(id)
                .map(producto -> ResponseEntity.ok(
                        new ResponseGeneralDto("OK", HttpStatus.OK.value(), "Cliente encontrado", producto))
                )
                .onErrorResume(e -> Mono.just(
                        ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(new ResponseGeneralDto("NOT_FOUND", HttpStatus.NOT_FOUND.value(), e.getMessage(), null))
                ));
    }



}
