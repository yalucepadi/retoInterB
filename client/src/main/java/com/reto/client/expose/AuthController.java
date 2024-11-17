package com.reto.client.expose;

import com.reto.client.model.request.AuthDto;
import com.reto.client.model.response.ResponseGeneralDto;
import com.reto.client.proxy.rest.auth.AuthService;
import com.reto.client.utils.AuthValidator;
import lombok.RequiredArgsConstructor;
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
    public Mono<ResponseEntity<ResponseGeneralDto>> createAuth(@RequestBody AuthDto authDto) {

        if (!AuthValidator.isValidClientId.test(authDto)) {
            return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseGeneralDto("ERROR", HttpStatus.BAD_REQUEST.value(), "Error: clientId is invalid", null)));
        }


        if (!AuthValidator.isValidPassword.test(authDto)) {
            return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseGeneralDto("ERROR", HttpStatus.BAD_REQUEST.value(), "Error: Password is invalid." + " Ensure it has at least 8 characters, " + "one uppercase letter, and one special character.", null)));
        }


        return authService.crearAuth(authDto).map(createdAuth -> {

            ResponseGeneralDto response = new ResponseGeneralDto("SUCCESS", HttpStatus.CREATED.value(), "Authentication created successfully", createdAuth);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }).onErrorResume(ex -> {

            ResponseGeneralDto response = new ResponseGeneralDto("ERROR", HttpStatus.INTERNAL_SERVER_ERROR.value(), "An unexpected error occurred", null);
            return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response));
        });
    }
}
