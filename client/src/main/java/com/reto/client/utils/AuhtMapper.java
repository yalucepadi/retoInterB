package com.reto.client.utils;

import com.reto.client.model.request.AuthDto;
import com.reto.client.model.request.AuthRequest;
import com.reto.client.model.request.ClientDto;
import com.reto.client.model.request.ClientRequest;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AuhtMapper {

    @Mapping(target = "user", ignore = true) // Se ignora porque se establece automáticamente
    @Mapping(target = "cliente", ignore = true)
    AuthRequest toAuth(AuthDto authDto);

    @Mapping(target = "clienteId", source = "cliente.id")
    @Mapping(target = "user", source = "cliente.nombres")
    AuthDto toDto(AuthRequest authRequest);



}
