package com.reto.client.expose;

import com.reto.client.model.request.AuthDto;
import com.reto.client.proxy.rest.auth.AuthService;
import com.reto.client.proxy.rest.auth.impl.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/create")
    public Mono<ResponseEntity<AuthDto>> createAuth(@RequestBody AuthDto authDto) {
        return authService.crearAuth(authDto)
                .map(createdAuth -> ResponseEntity.status(HttpStatus.CREATED).body(createdAuth));
    }
}
