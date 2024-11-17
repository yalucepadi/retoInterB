package com.reto.product.utils;

import com.reto.product.model.request.ProductoDto;
import com.reto.product.model.request.ProductoRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface ProductoMapper {



    @Mapping(target = "id", source = "id")
    @Mapping(target = "tipoProducto", source = "tipoProducto")
    @Mapping(target = "nombre", source = "nombre")
    @Mapping(target = "saldo", source = "saldo")
    ProductoRequest toProducto(ProductoDto productoDto);


    @Mapping(target = "id", source = "id")
    @Mapping(target = "tipoProducto", source = "tipoProducto")
    @Mapping(target = "nombre", source = "nombre")
    @Mapping(target = "saldo", source = "saldo")
    ProductoDto toDto(ProductoRequest productoRequest);


    void updateProductoFromDto(ProductoDto dto, @MappingTarget ProductoRequest request);

}
