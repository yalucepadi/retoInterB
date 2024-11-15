package com.reto.client.utils;

import com.reto.client.model.request.ClientDto;
import com.reto.client.model.request.ClientRequest;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")

public interface ClientMapper {
    SecureIdGenerator generator = new SecureIdGenerator();
    @Mapping(target = "id", source = "id")

    @Mapping(target = "apellidos", source = "apellidos")
    @Mapping(target = "nombres", source = "nombres")
    @Mapping(target = "tipoDocumento", source = "tipoDocumento")
    @Mapping(target = "numeroDocumento", source = "numeroDocumento")
    @Mapping(target = "productosFinancieros", source = "productosFinancieros")


    ClientRequest toClient(ClientDto clientDto);

    @Mapping(target = "id", source = "id")

    @Mapping(target = "apellidos", source = "apellidos")
    @Mapping(target = "nombres", source = "nombres")
    @Mapping(target = "tipoDocumento", source = "tipoDocumento")
    @Mapping(target = "numeroDocumento", source = "numeroDocumento")
    @Mapping(target = "productosFinancieros", source = "productosFinancieros")

    ClientDto toDto(ClientRequest clientRequest);
    @AfterMapping
    default void generarCodigoUnico(@MappingTarget ClientRequest clientRequest) {

        String codigoUnico = generator.generateSecureId();
        clientRequest.setCodigoUnico(codigoUnico);
    }

    @AfterMapping
    default void generarCodigoUnicoDto(@MappingTarget ClientDto clientDto) {

        String codigoUnico = generator.generateSecureId();
        clientDto.setCodigoUnico(codigoUnico);
    }
    void updateClientFromDto(ClientDto dto, @MappingTarget ClientRequest request);


}
