package com.reto.client.config;
import com.reto.client.model.request.AuthDto;
import com.reto.client.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
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

    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public Mono<ResponseEntity<?>> login(@RequestBody AuthDto request) {
        // Aquí deberías validar las credenciales contra tu base de datos
        // Este es un ejemplo simplificado
        if ("admin".equals(request.getUser()) && "admin".equals(request.getPassword())) {
            String token = jwtUtil.generateToken(request.getUser());
            return Mono.just(ResponseEntity.ok(new AuthResponse(token)));
        }
        return Mono.just(ResponseEntity.badRequest().build());
    }
}

record AuthRequest(String username, String password) {}
record AuthResponse(String token) {}