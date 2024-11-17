package com.reto.client.utils;

import com.reto.client.model.request.AuthDto;
import com.reto.client.model.request.AuthRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public interface AuhtMapper {

    @Mapping(target = "user", ignore = true) // Se ignora porque se establece automáticamente
    @Mapping(target = "cliente", ignore = true)
    AuthRequest toAuth(AuthDto authDto);

    @Mapping(target = "clienteId", source = "cliente.id")
    @Mapping(target = "user", source = "cliente.nombres")
    AuthDto toDto(AuthRequest authRequest);

    default void setPassword(AuthRequest authRequest, PasswordEncoder passwordEncoder) {
        authRequest.setPassword(passwordEncoder.encode(authRequest.getPassword()));
    }


}
